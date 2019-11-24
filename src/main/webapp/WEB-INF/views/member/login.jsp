<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<%@include file="/WEB-INF/views/include/incResource.jspf" %>
	<title>로그인</title>
  <style type="text/css">
    body {background-color: #DADADA;}
    body > .grid {height: 100%;}
    	   .image { margin-top: -100px;}
    .column {max-width: 450px;}
    .grid{}
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
			console.log(res);
			if(res.success){
				location.replace("/");
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
	  
      $('.ui.form')
        .form({
          fields: {
            email: {
              identifier  : 'email',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Please enter your e-mail'
                },
                {
                  type   : 'email',
                  prompt : 'Please enter a valid e-mail'
                }
              ]
            },
            password: {
              identifier  : 'password',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Please enter your password'
                },
                {
                  type   : 'length[6]',
                  prompt : 'Your password must be at least 6 characters'
                }
              ]
            }
          }
        })
      ;
    })
  ;
  </script>	
</head>
<body>
	<!--상단 고정된 헤더 -->
	<%@include file="/WEB-INF/views/include/header.jspf" %>
	
	<!--중앙 몸통 시작 -->
	
		<div class="ui middle aligned center aligned grid">
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
