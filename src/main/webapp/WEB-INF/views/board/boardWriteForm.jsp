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
		
	
		var errors = {
			INVALID_title : '   -제목을 입력해주세요',
			INVALID_EMAIL : '   -올바른 이메일을 입력해주세요',
			INVALID_NICK : '  -작성자을 입력해주세요. (한글, 영문, 숫자만 허용)',
			INVALID_CONTENT: ' -내용을 입력해주세요.(텍스트입력 필수)',
		}
		
		var valid = {
				title: null,
				nickname: null,
				email : null,
				summernote : null
		}
		
		function fnBoardList(){
			location.href="/board/boardList"
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
		
		function putAttachFile(imgUrl, fileSize, fileId, fileName){
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
						var url = data.imgUrl;
						var fileId = data.fileId;
						var fileSize = data.length;
						var fileName = data.originName;
						successCallback(url, fileSize, fileId, fileName);
					}else if(!data.success){					
							alert(data.message);
					}
				},
				error : function(xhr, status, err){
					console.log(xhr);
					console.log(status);
					console.log(err);
					var msg = xhr.responseJSON ? xhr.responseJSON.cause : "TOO_BIG";
					if(msg=='TOO_BIG'){
						alert("파일이 너무큽니다.");
						
					}
					
				}
			});
		}
	
		function _disableForm() {
			//$('#submit').off('click');
			//$('#submit').hide();
		}
	
		function _enableForm(){
			for( var k in valid ) { 
				if ( !valid[k] ) {
					return;
				}
			}
			//$('#submit').show();
			//$ ('#submit').on('click', sendForm); 
		}
		
		function disableForm(selector, errorCode) {
			if ( errorCode ) {
				var t = $(selector).parent().find('.error-msg');
				t.text(errors[errorCode]);
				
			}
			$(selector).parent().removeClass('success');
			$(selector).parent().addClass('error');
			_disableForm();
		}
		
		function enableForm(selector) {
				$(selector).parent().removeClass('error');
				$(selector).parent().addClass('success');
			
			_enableForm();
		}
		
		function checkUnique( propName, value, errorCallback, successCallback, $el) {
			// el : data-rule=""
			if ( $el && $el.length > 0 ) {
				// 정규식으로 검사해봄
				var rule = $el.data('rule');
				if ( rule ) {
					var re = new RegExp(rule);
					var val = $el.val();
					if ( !re.test(val)) {
						var errorCode = $el.data('error');
						errorCallback(errorCode);
						valid[propName] = false;
						return;
					}
				}else{ // data-rule(정규식) 값이 없으면 단순 공백체크만함
					if(propName == 'summernote'){
						var text = $("#summernote").summernote("code").replace(/&nbsp;|<\/?[^>]+(>|$)/g, "").trim();
					}else{
						var text = $el.val().replace(/&nbsp;|<\/?[^>]+(>|$)/g, "").trim();
					}
	     			
					if (text.length == 0) {
							var errorCode = $el.data('error');
							errorCallback(errorCode);
							valid[propName] = false;
							return;
	      			} 
					/*else {
	         			  $("#btnForm").removeAttr("disabled"); 
	      				console.log('뭔가있음');
	      			}*/
				}
				valid[propName] = true;
				successCallback();		
				
			}
		}
		
		function sendForm(){
			// valid.loginId , vaild['loginId'] 
			for( var k in valid ) { 
				if ( !valid[k] ) {
					alert ('입력 값을 확인해주세요.');
					return false;
				}
			}
			
			//e.preventDefault();
			
			var formData  = new FormData();
			formData.append("title", $("input[name=title]").val());
			formData.append("writer", $("input[name=writer]").val());
			formData.append("email", $("input[name=email]").val());
			formData.append("content", $("textarea[name=content]").val());
			
			$.ajax({
				method:"POST",
				url:"/board/doWrite",
				processData: false, 
				contentType: false,
				data:formData,
				success: function(data){
					if(data=="success"){
						alert('글이 작성되었습니다.');
						location.href="/board/boardList"
					}
				},
				error:function(error){
					
				}
			})
			
			return false;
		}
		
		function insertImgTag(imgUrl) {
			$('#summernote').summernote('insertImage', imgUrl);
		}
		
		

		var fnTitle = function () {
			var val = $('#title').val();
			checkUnique ( 'title', val, function(errorCode) {
				disableForm('#title', errorCode);
			}, function(){
				enableForm('#title');
			},
			$('#title') );
		};			
		var fnNickname = function () {
			var val = $('#nickname').val();
			checkUnique ( 'nickname', val, function(errorCode) {
				disableForm('#nickname', errorCode);
			}, function(){
				enableForm('#nickname');
			},
			$('#nickname') );
		};
		var fnEmail = function () {
			var val = $('#email').val();
			checkUnique ( 'email', val, function(errorCode) {
				disableForm('#email', errorCode);
			}, function(){
				enableForm('#email');
			},
			$('#email') );
		};	
			var fnContent = function () {
			var val = $('#summernote').val();
			checkUnique ( 'summernote', val, function(errorCode) {
				disableForm('#summernote', errorCode);
			}, function(){
				enableForm('#summernote');
			},
			$('#summernote') );
		};
		
		
		
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
			
			fnTitle();
			fnNickname();
			fnEmail();
			fnContent();
			
			$('#summernote').summernote({}).on('summernote.change', function() {fnContent();});
			$('#title').on('propertychange change keyup paste input ', function() {fnTitle();});
			$('#nickname').on('propertychange change keyup paste input ', function() {fnNickname();});
			$('#email').on('propertychange change keyup paste input ', function() {fnEmail();});
			
			
			$(".action input:text, .action .attachBtn").click(function() {
				 $(this).parent().find("input:file").click();
			});
			
			//첨부파일 업로드시
			$(".file").on("change", function () {	
				var file = this.files[0]				
				var fileName = file.name,
				 	fileSize = file.size,
				 	idx = $(".file").index(this);
				
/*  				if(fileSize > 3145728){ //3MB = 3145728 BYTES
					alert('3MB 미만의 파일만 업로드 부탁드립니다.')
				}  */
				
				tempFileUpload(file, putAttachFile);
	        });
			
			
 			//작성하기 버튼 클릭
			$("#submit").click(function(){
				sendForm();
			}); 
			
			//파일삭제
			$('#file-list').click(function(e){
				console.log(e.target);
				if ($(e.target).hasClass('detachBtn')) {
					deleteUpfile($(e.target).parent());
				}
			});
			
		}); //$(document).ready
			
	</script>
	
	<style type="text/css">
		html {font-size: 14px !important;}
		.ui.action.input input[type="file"] {display: none;}
		.field .error-msg, .field .success-msg {display:none;}
		.field.error .error-msg, .field.success .success-msg {display:inline;}
		.field.success label {color:#84ae44 !important;}
		.field.success input {background: #f9ffee !important;border-color: #c8fb2b !important;color: #84ae44 !important}
		.field.success textarea {!important;border-color: #c8fb2b !important;color: #84ae44 !important}
	</style>
</head>
<body>
	<!--상단 고정된 헤더 -->
	<%@include file="/WEB-INF/views/include/header.jspf" %>
	
	<!--중앙 몸통 시작 -->
	<div class="main-content">
	<h1 class="ui header">게시글 작성 </h1>
	<div class="ui divider"></div>
			<div class="ui form">
			<form name="boardForm" enctype="multipart/form-data" method="post" accept-charset="UTF-8">
			  	<div class="field">
	  				<label>*제목  <span class="error-msg"> -제목을 입력해주세요</span><span class="success-msg"> -제목 입력 OK</span></label>
			       	 <input type="text" id="title" name="title" placeholder="제목 입력" 
			       	 	data-error="INVALID_TITLE">
	 			</div>
	 			<div class="field">															
			        <label>*작성자  <span class="error-msg"> -작성자을 입력해주세요</span><span class="success-msg"> -작성자 입력OK</span></label>
			       	 <input type="text" id="nickname" name="writer" placeholder="작성자 입력" value="" data-rule="^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]+$" data-error="INVALID_NICK">
			      </div>
				  <div class="field">
				    <label>*이메일  <span class="error-msg">-올바른 이메일을 입력해주세요</span><span class="success-msg"> - 이메일 입력 OK</span></label>
					 <input type="text" id= "email" name="email" placeholder="이메일 입력"  value=""
					 	data-rule="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"
					 	data-error="INVALID_EMAIL">
				  </div>
		  		<div class="field">
		    		 <label>*내용<span class="error-msg"></span><span class="success-msg"> - 내용 입력 OK</span></label>
		   			 <textarea id="summernote" name="content" placeholder="내용을 입력하세요..."
		   			 	name="comtent" 					 	
					 	data-error="INVALID_CONTENT"
		   			 ></textarea>
		  		</div>
		  		<!-- 첨푸파일 -->
		  		<div class="field">
		  			<label>파일첨부&nbsp;&nbsp;&nbsp;*각파일 3MB이하 최대 3개 까지 업로드 가능합니다</label>
					<div class="ui action input">
		  				<input type="text" placeholder="파일첨부" class="fileName" readonly>
		  				<input type="file" name="file" class="file" value="" data-idx="0">
		  			  <div class="ui icon button attachBtn">
		   			 	<i class="attach icon"></i>
		  			  </div>
					</div><br/>
					<div class="ui positive message" id="file-list" >
  						 <h4>*첨부된 파일 목록</h4>
					</div>
				</div>
				<!-- //첨푸파일 -->
				</form>	
			</div>			
			<div style="padding: 50px;text-align: center;">
				<button class="ui primary button" id="submit">작성하기</button>
				<button class="ui ui button" id="cancel" onclick="javascript:fnBoardList();">돌아가기</button>
			</div>
	</div>
	<!--하단고정된 푸터 -->
	<%@include file="/WEB-INF/views/include/footer.jspf" %>
	
</body>
<script>
	$("#email").val(loginUser.email);
	$("#nickname").val(loginUser.nickname);
</script>
</html>
