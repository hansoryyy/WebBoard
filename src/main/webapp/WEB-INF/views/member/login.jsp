<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<%@include file="/WEB-INF/views/include/incResource.jspf" %>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
	
	<title>로그인</title>

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
  
  function fnSocialLogin(channel){
	  
	 /* $.ajax({
		  type : "post",
		  url : "/login/socialLogin",
		  data : {channel : channel}
	  	  success : function(){
	  		  
	  	  }
	  }) */
	  
	  openPopup("/login/social/" + channel, 0, 0, "yes");
  }
  
  
  var openPopup = function (url, winname, width, height, scrolltype,xpo,ypo,alignGb){
	   var xposition=xpo, yposition=ypo;
	   
	   if (alignGb === undefined) {
		   alignGb = 0;
	   }
	   
	   if (alignGb==0){
		   xposition = (screen.width - width) / 2;
		   yposition = (screen.height - height) / 2;
		   
	   }
	   if(scrolltype === undefined) { 
		   scrolltype = 'no';
	   }
	   var args = "width=" + width + "," + "height=" + height + "," + "location=0," + "menubar=0," + "resizable=no,"
	         + "scrollbars=" + scrolltype + "," + "status=0," + "titlebar=0," + "toolbar=0," + "hotkeys=0,"
			 + "screenx=" + xposition + ","  //NN Only
			 + "screeny=" + yposition + ","  //NN Only
			 + "left=" + xposition + ","     //IE Only
			 + "top=" + yposition;           //IE Only

	   if(url.indexOf('?') > -1) {
		   var newWin=window.open(url+"&popupYn=Y",winname,args);
		   return newWin;
		  // newWin.focus();
	   } else {
		   var newWin=window.open(url+"?popupYn=Y",winname,args);
		   return newWin;
		   //newWin.focus();
	   }
	};
  
	
  $(document).ready(function() {
	  
	 	  $('#btn_login').click(processLogin); 
  
	  	  $('div.ui.form input').on('keyup', function(e) {
	  		  e.preventDefault();
	  		  if ( e.keyCode === 13) {
	  			  processLogin(e);
	  		  }
	  		  return false;
	  	  });
    })
  </script>
  <style>
	.btn-social-login { transition: all .2s;outline: 0;border: 1px solid transparent; padding: .5rem !important;border-radius: 50%;color: #fff;}
	.btn-social-login:focus {box-shadow: 0 0 0 .2rem rgba(0,123,255,.25);}
	.text-dark { color: #343a40!important; }
	.ui.teal.buttons .button, .ui.teal.button {background-color: #2185D0;color: #FFFFFF;text-shadow: none;background-image: none;}
	.btn-social-login {cursor:pointer;}
	.image { margin-top: -100px;}
    .column {max-width: 450px;}
  </style>
</head>
<body>
	<!--상단 고정된 헤더 -->
	<%@include file="/WEB-INF/views/include/header.jspf" %>
	
	<!--중앙 몸통 시작 -->
	
	<div class="ui middle aligned center aligned grid" style="min-height: 926px;">
			  <div class="column">
			    <h2 class="ui teal image header">
			<!--<img src="assets/images/logo.png" class="image"> -->
			      <div class="content" style="color: #000000;">
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
				        <div class="ui fluid large teal submit button" id="btn_login" style="margin-bottom:10px">Login</div>
				        <div class="" style="text-align:center;">
<!-- 							<button class='btn-social-login' style='background:#D93025' onclick="javascript:fnSocialLogin('google');"><i class="xi-3x xi-google"></i></button>
							<button class='btn-social-login' style='background:#4267B2' onclick="javascript:fnSocialLogin('facebook');"><i class="xi-3x xi-facebook"></i></button> -->
							<!-- <button class='btn-social-login' style='background:#55ACEE'><i class="xi-3x xi-twitter"></i></button> -->
							<!-- <button class='btn-social-login' style='background:#24292E'><i class="xi-3x xi-github"></i></button> -->
							<button class='btn-social-login' style='background:#1FC700' onclick="javascript:fnSocialLogin('naver');"><i class="xi-3x xi-naver"></i></button>
							<button class='btn-social-login' style='background:#FFEB00' onclick="javascript:fnSocialLogin('kakao');"><i class="xi-3x xi-kakaotalk text-dark"></i></button>
						</div>
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
