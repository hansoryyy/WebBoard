<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<%@include file="/WEB-INF/views/include/incResource.jspf" %>
	
	<title>회원가입</title>
	
<script type="text/javascript">

	$(document).ready(function(){
		
		$('#btn_join').click(function(){
			
			var data = $('#joinForm').serialize();
			
			$.ajax({
				url : "/member/memberJoin",
				method : "POST" ,
				data : data,
				success : function(res){
					console.log(res);
				}
				
				
			});
			
			
		});
	});
	


</script>	
	
</head>
<body>
	<!--상단 고정된 헤더 -->
	<%@include file="/WEB-INF/views/include/header.jspf" %>
	<!--중앙 몸통 시작 -->
		<div class="ui main text container">
			<form class="ui form" id="joinForm">
				  <h4 class="ui dividing header">회원가입</h4>
			      <div class="field">
			        <label>아이디</label>
			       	 <input type="text" name="loginId" placeholder="아이디 입력">
			      </div>
			      <div class="two fields">
			        
			        <div class="field">
			        <label>비밀번호</label>
			       	  <input type="text" name="loginPw" placeholder="비밀번호 입력">
			        </div>
			        <div class="field">
			        <label>비밀번호 확인</label>
			       	  <input type="text" placeholder="비밀번호 확인">
			        </div>
			       	 
			      </div>
			      <div class="field">
			        <label>닉네임</label>
			       	 <input type="text" name="nickname" placeholder="닉네임 입력">
			      </div>
				  <div class="field">
				    <label>이메일</label>
						 <input type="text" name="email" placeholder="이메일 입력">
				  </div>
				  <div class="ui button" id="btn_join" tabindex="0">가입하기</div>
				</form>
		</div>
	<!--하단고정된 푸터 -->
	<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>
</html>
