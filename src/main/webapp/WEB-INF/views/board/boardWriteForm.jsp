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
				focus:true
			});
			
			$(".action input:text, .action .button").click(function() {
				 $(this).parent().find("input:file").click();
			});
		});
	
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
	<form action="/board/doWrite" enctype="multipart/form-data" method="post" accept-charset="UTF-8">
		<div class="ui form">
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
	  		<div class="ui action input">
  				<input type="text" placeholder="첨부파일" readonly>
  				<input type="file" name="file">
  			  <div class="ui icon button">
   			 	<i class="attach icon"></i>
  			  </div>
			</div>
			<div>
				<input type="submit" value="전송">
			</div>
		</div>
	</form>	
	</div>
	
	<!--하단고정된 푸터 -->
	<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>
</html>
