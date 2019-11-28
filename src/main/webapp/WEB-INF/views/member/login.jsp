<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<%@include file="/WEB-INF/views/include/incResource.jspf" %>
	<title>로그인</title>
  <style type="text/css">
    body {background-color: #DADADA;}
   	.image { margin-top: -100px;}
    .column {max-width: 450px;}
  </style>
  <script>
 
  function processLogin(e){
	  e.preventDefault();
	  // loginId=12222&loginPw=3333
	$.ajax({
		type : "post",
		url : "/login/loginAction",
		data : {
			loginId : $('#loginId').val(),
			loginPasswd : $('#loginPasswd').val()
		},
		success : function(res){
			if(res.success){
				var nickname = res.member.nickname;
				alert(nickname +'님 환영합니다 ^^');
				location.href = '/';
			}else{
				alert('아이디와 비밀번호를 확인해주세요');
			}
		}
	});
	return false;
  }
  
	
  $(document).ready(function() {
	  
	 	  $('#btn_login').click(processLogin); 
  
	  	  $('div.ui.form input').on('keyup', function(e) {
	  		  // console.log(e.target.value);
	  		  e.preventDefault();
	  		  if ( e.keyCode === 13) {
	  			  processLogin(e);
	  		  }
	  		  return false;
	  	  });
    })
  </script>	
</head>
<body>
	<!--상단 고정된 헤더 -->
	<%@include file="/WEB-INF/views/include/header.jspf" %>
	
	<!--중앙 몸통 시작 -->
	
	<div class="ui middle aligned center aligned grid" style="min-height: 926px;">
			  <div class="column">
			    <h2 class="ui teal image header">
			<!--<img src="assets/images/logo.png" class="image"> -->
			      <div class="content">
			        	로그인을 해주세요 ! 
			      </div>
			    </h2>
			    <div class="ui large form">
				      <div class="ui stacked segment">
				        <div class="field">
				          <div class="ui left icon input">
				            <i class="user icon"></i>
				            <input type="text" name="loginId" id="loginId" placeholder="ID를 입력해주세요 ! ">
				          </div>
				        </div>
				        <div class="field">
				          <div class="ui left icon input">
				            <i class="lock icon"></i>
				            <input type="password" name="loginPasswd" id="loginPasswd" placeholder="비밀번호를 입력해주세요 ! ">
				          </div>
				        </div>
				        <div class="ui fluid large teal submit button" id="btn_login">Login</div>
				      </div>
			      <div class="ui error message"></div>
			    </div>
			
				    <div class="ui message">
				    	 아직 회원이 아니신가요 ? &nbsp; <a href="/member/join">회원가입 </a>
				    </div>
			  </div>
			</div>

	<!--하단고정된 푸터 -->
	<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>
</html>
