<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/include/incResource.jspf" %>
	
	<!-- summernote js, css -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	<script type="text/javascript" src="/resources/summernote/summernote.js"></script>
	<link rel="stylesheet" type="text/css" href="/resources/summernote/summernote.css">
	<!-- summernote js, css -->

	<script type="text/javascript">
		
		
		$(document).ready(function(){
			
			$('#summernote').summernote({
				height:300,
				focus:true,
				callbacks:{
					onImageUpload : function (files){	//이미지 업로드시 DB에 저장하고 content 영역에 뿌려줌.
						tempFileUpload(files[0], insertImgTag);
					}
				} 
			});
			
			
			$(".action input:text, .action .attachBtn").click(function() {
				 $(this).parent().find("input:file").click();
			});
			
			//첨부파일 삭제시
			$(" .detachBtn").click(function(){
				var idx = $(this).index(".detachBtn");
				$(".file").eq(idx).val("");
				$(".fileName").eq(idx).val("");
				return;
			});
			
			//첨부파일 업로드시
			$(".file").on("change", function () {	
				var file = this.files[0]				
				var fileName = file.name,
				 	fileSize = file.size,
				 	idx = $(".file").index(this);
				
				console.log('object File : ' + file );
				
				// tempFileUpload(file, putAttachFile(fileName, fileSize));
				tempFileUpload(file, putAttachFile);
	        });
			
			
			//작성하기 버튼 클릭
			$("#submit").click(function(){
				//var data = $("form[name=boardform]").serialize();
				var formData  = new FormData();
				
				formData.append("title", $("input[name=title]").val());
				formData.append("email", $("input[name=email]").val());
				formData.append("content", $("textarea[name=content]").val());
			 /* formData.append("file",$("input[name=file]")[0].files[0]);
				formData.append("file",$("input[name=file]")[1].files[0]);
				formData.append("file",$("input[name=file]")[2].files[0]); */
	
				$.ajax({
					method:"POST",
					url:"/board/doWrite",
					processData: false, 
					contentType: false,
					data:formData,
					success: function(data){
						if(data=="success"){
							alert('글이 작성되었습니다.');
						}
					},
					error:function(error){
						
					}
				})
			});
			
			$('#file-list').click(function(e){
				console.log(e.target);
				if ($(e.target).hasClass('detachBtn')) {
					deleteUpfile($(e.target).parent());
				}
			});
			
		}); //$(document).ready
		
		function insertImgTag(imgUrl) {
			$('#summernote').summernote('insertImage', imgUrl);
		}
		function deleteUpfile(fileElem) {
			var fileId = $(fileElem).attr("id");
			console.log(fileId)
			$.ajax({
				data : {fileId : fileId},
				type : "POST",
				url : "/board/tempFileDelete",
				success : function (data){
					if (data.success) {
						$(fileElem).remove();
					}
				}
			})
		}
		function putAttachFile(fileName, fileSize, fileId){
			$('#file-list').append('<div id="' + fileId + '"><span>'+fileName+' ( ' + parseInt(fileSize/1024) +' KB )</span><a class= "detachBtn" style="cursor:pointer">[삭제]</a></div>');
		}
		
		function tempFileUpload(file, successCallback){
			var fileFormData = new FormData();
			fileFormData.append('file', file); // [ img, pdf]
			
			$.ajax({
				data : fileFormData,
				type : "POST",
				url : "/board/tempFileUpload",
				enctype:"multipart/form-data",
				cache : false,
				contentType : false,
				processData : false,
				success : function (data){
					
					if (data.success && successCallback) {
						var url = data.img_url;
						var fileId = data.fileId;
						var fileSize = data.length;
						
						
						successCallback(url, fileSize, fileId);
					}
				}
			})
		}
	
	</script>
	
	<style type="text/css">
		html {font-size: 14px !important;}
		.ui.action.input input[type="file"] {display: none;}
	</style>
</head>
<body>
	<!--상단 고정된 헤더 -->
	<%@include file="/WEB-INF/views/include/header.jspf" %>
	
	<!--중앙 몸통 시작 -->
	<div style="padding: 50px;">
			<div class="ui form">
			<form name="boardForm" action="/board/doWrite" enctype="multipart/form-data" method="post" accept-charset="UTF-8">
			  	<div class="field">
	   				<label>제목</label>
	  				<input type="text"  name="title" placeholder="제목을 입력하세요...">
	 			</div>
	 			<div class="field">
	   				<label>이메일</label>
	  				<input type="text" name="email" placeholder="이메일을 입력하세요 ...">
	 			</div>
		  		<div class="field">
		    		 <label>내용</label>
		   			 <textarea id="summernote" name="content" placeholder="내용을 입력하세요..."></textarea>
		  		</div>
		  		<!-- 첨푸파일 -->
		  		<div class="field">
					<div class="ui action input">
		  				<input type="text" placeholder="파일첨부" class="fileName" readonly>
		  				<input type="file" name="file" class="file" value="" data-idx="0">
		  			  <div class="ui icon button attachBtn">
		   			 	<i class="attach icon"></i>
		  			  </div>
<!-- 		  		<div class="ui icon button detachBtn">
		   			 	<i class="x icon"></i>
		  			  </div> -->
					</div><br/>
					<div class="ui positive message" id="file-list" >
  						 <h4>*첨부된 파일 목록</h4>
					</div>
					<!-- 
					<div class="ui action input">
		  				<input type="text" placeholder="첨부파일 2" class="fileName" readonly>
		  				<input type="file" name="file" class="file" value="" data-idx="1">
		  			  <div class="ui icon button attachBtn">
		   			 	<i class="attach icon"></i>
		  			  </div>
		  			  <div class="ui icon button detachBtn">
		   			 	<i class="x icon"></i>
		  			  </div>
					</div><br/><br/>
					<div class="ui action input">
		  				<input type="text" placeholder="첨부파일 3" class="fileName" readonly>
		  				<input type="file" name="file"class="file" value="" data-idx="2">
		  			  <div class="ui icon button attachBtn">
		   			 	<i class="attach icon"></i>
		  			  </div>
		  			  <div class="ui icon button detachBtn">
		   			 	<i class="x icon"></i>
		  			  </div>
					</div>
					 -->
				</div>
				<!-- //첨푸파일 -->
				</form>	
			</div>			
			<div style="padding: 50px;text-align: center;">
				<button class="ui primary button" id="submit">작성하기</button>
				<button class="ui ui button" id="cancel">돌아가기</button>
			</div>
	</div>
	
	<!--하단고정된 푸터 -->
	<%@include file="/WEB-INF/views/include/footer.jspf" %>
	
</body>
</html>
