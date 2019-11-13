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
	
	function insertImgTag(imgUrl) {
		$('#summernote').summernote('insertImage', imgUrl);
	}
	
		$(document).ready(function(){
			
			
			$('#summernote').summernote({
				height:300,
				focus:true,
				callbacks:{
					onImageUpload : function (files){	//이미지 업로드시 DB에 저장하고 content 영역에 뿌려줌.
						summernoteImageUpload(files[0], insertImgTag);
					}
				} 
			});
			
			$(".action input:text, .action .attachBtn").click(function() {
				 $(this).parent().find("input:file").click();
			});
			
			//첨부파일 삭제시
			$(".action .detachBtn").click(function(){
				var idx = $(this).index(".detachBtn");
				$(".file").eq(idx).val("");
				$(".fileName").eq(idx).val("");
				return;
			});
			
			//첨부파일 업로드시
			$(".file").on("change", function () {
				var file = this.files[0],
				 	fileName = file.name,
				 	fileSize = file.size,
				 	idx = $(".file").index(this);
				
		/* 		var fileValue = $(file).val().split("\\");
				var fileName = fileValue[fileValue.length-1]; // 파일명 */

			
				idx = $(this).data('idx');
				
					var fileObj = $('.file'); // [file, file, file]
				if(fileSize>3072){
					alert("3MB 이하의 파일만 업로드해주세요.");
					$(this).val("");
					return;
				}
				
				
				summernoteImageUpload(file, function (dddddd) {
					$('#file-list').append('<span>'+fileName+'</span>');
				});
				

				//alert("idx: " + fileName + " @ " + fileSize + "bytes");
				//CustomFileHandlingFunction(file);
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
			
		}); //$(document).ready

		
		function summernoteImageUpload(file, successCallback){
			var formData = new FormData();
			formData.append('file', file);
			
			$.ajax({
				data : formData,
				type : "POST",
				url : "/board/summernoteImageUpload",
				enctype:"multipart/form-data",
				cache : false,
				contentType : false,
				processData : false,
				success : function (data){
					
					if (data.success && successCallback) {
						var url = data.img_url;
						successCallback(url);
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
		  			<label>첨부파일&nbsp;&nbsp;&nbsp;*최대3개의 파일 업로 가능하며 각파일의 용량은 3MB이하 파일을 업로드 해주세요.</label>
		  			<div class="ui action input" id="file-list">
		  				
		  			</div>
			  		<div class="ui action input">
		  				<input type="text" placeholder="첨부파일 1" class="fileName" readonly>
		  				<input type="file" name="file" class="file" value="" data-idx="0">
		  			  <div class="ui icon button attachBtn">
		   			 	<i class="attach icon"></i>
		  			  </div>
		  			  <div class="ui icon button detachBtn">
		   			 	<i class="x icon"></i>
		  			  </div>
					</div><br/><br/>
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
