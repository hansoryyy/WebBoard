<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<%@include file="/WEB-INF/views/include/incResource.jspf" %>
	
	<title>회원가입</title>
<style type="text/css">
.field .error-msg, .field .success-msg {display:none;}
.field.error .error-msg, .field.success .success-msg {display:inline;}
.field.success label {color:#84ae44 !important;}
.field.success input {background: #f4ffdf !important;border-color: #c8fb2b !important;color: #84ae44 !important}
</style>
<script type="text/javascript">

	var errors = {
		DUP_VALUE : '    - * 중복된 값입니다',
		INVALID_ID : '   - * 잘못된 형식(4~12글자, 소문자또는숫자)',
		INVALID_NICK : '   - * 잘못된 형식입니다 (한글, 영문, 숫자만 허용)',
		INVALID_EMAIL : '   - * 잘못된 이메일입니다',
		DIFF_PW: '   - * 비번이 서로 다릅니다.',
		SHORT_PW: '   - * 비번이 짧습니다.(6자리 이상)'
	}
	
	var valid = {
		loginId: null,
		nickname: null,
		password : null,
		email : null
	}
	
	function sendForm(e){
		// valid.loginId , vaild['loginId'] 
		for( var k in valid ) { 
			if ( !valid[k] ) {
				alert ('폼 검증해주세요');
				return false;
			}
		}
		
		e.preventDefault();
		
		var data = $('#joinForm').serialize();
		$.ajax({
			url : "/member/memberJoin",
			method : "POST" ,
			data : data,
			success : function(res){
				if (res.success) {
					alert('가입에 성공했습니다.');
				} else {
					alert('가입에 실패했습니다.');
				}
			}
		});
		return false;
	}
	
	function _enableForm(){
		for( var k in valid ) { 
			if ( !valid[k] ) {
				// alert ('폼 검증해주세요');
				return;
			}
		}
		$('#btn_join').on('click', sendForm);
	}
	
	function _disableForm() {
		$('#btn_join').off('click');
	}
	
	function disableForm(selector, errorCode) {
		// 'DUP_VALUE', 'INVALID_FORM', 'DIFF_PW'
		if ( errorCode ) {
			console.log('들어오니?');
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
					return;
				}
			}
		}
	
		$.ajax({
			url : "/member/memberCheck",
			method : "GET",
			datatype : "json",
			data : { prop: propName, value : value},
			success : function(res){
				var existId = res.existId;
				if(existId == 'DUP_VALUE'){
					errorCallback('DUP_VALUE');
					valid[propName] = false;
				}else{
					successCallback();
					valid[propName] = true;
				}
			}
		});
		
	}
	
	
	$(document).ready(function(){
		
		$('#loginId').change(function () {
			var val = $('#loginId').val();
			checkUnique ( 'loginId', val, 
					function(errorCode) {
						disableForm('#loginId', errorCode);
					},function(){
						enableForm('#loginId');
					},
					$('#loginId') );
		});
		
		$('#nickname').change(function () {
			var val = $('#nickname').val();
			checkUnique ( 'nickname', val, function(errorCode) {
				disableForm('#nickname', errorCode);
			}, function(){
				enableForm('#nickname');
			},
			$('#nickname') );
		});
		
		var fnEmail = function () {
			var val = $('#email').val();
			checkUnique ( 'email', val, function(errorCode) {
				disableForm('#email', errorCode);
			}, function(){
				enableForm('#email');
			},
			$('#email') );
		};
		
		$('#email').change(fnEmail);
		$('#email').on('blur',fnEmail);
		
		
		
		$('#loginPw, #loginPw2').change(function () {
			var loginPw = $('#loginPw').val();
			var loginPw2 = $('#loginPw2').val();
			
			if(loginPw !== loginPw2 ){
				disableForm('#loginPw', 'DIFF_PW');
				disableForm('#loginPw2');
				valid['password'] = false;
			}else if(loginPw.length <= 5 ){
				disableForm('#loginPw', 'SHORT_PW');
				valid['password'] = false;
			}else{
				enableForm('#loginPw');
				enableForm('#loginPw2');
				// valid.password = true;
				valid['password'] = true;
			}
		});
		
		//회원가입 버튼 클릭시
		// $('#btn_join').click(sendForm);

	});
	
</script>	
	
</head>
<body>
	<!--상단 고정된 헤더 -->
	<%@include file="/WEB-INF/views/include/header.jspf" %>
	<!--중앙 몸통 시작 -->

		
	 <div style="padding: 50px; min-height: 863px;">
	 	<h1 class="ui header">회원가입 </h1>
		<div class="ui divider"></div>
		<div class="ui main text container">
			<form class="ui form" id="joinForm">
				  <!-- <h4 class="ui dividing header">회원가입</h4> -->
			      <div class="field">
			        <label>아이디 <span class="error-msg"></span><span class="success-msg"> - *사용가능한 아이디입니다</span></label>
		       		 <input type="text" id="loginId" name="loginId" placeholder="아이디 입력" 
		       		 	data-rule="^[a-z][a-z0-9]{3,11}$" 
		       		 	data-error="INVALID_ID">
			      </div>
			      
			      <div class="two fields"> 
			        <div class="field">
			        <label>비밀번호<span class="error-msg"></span><span class="success-msg"> - *사용가능한 비번입니다.</span></label> 
			       	  <input type="password" id="loginPw" name="loginPw" placeholder="비밀번호 입력">
			        </div>
			        <div class="field">
			        <label>비밀번호 재입력</label>
			       	  <input type="password" id="loginPw2" placeholder="비밀번호 재입력">
			        </div>
			      </div>
			      
			      <div class="field">
			        <label>닉네임  <span class="error-msg"> - *중복된 닉네임입니다</span><span class="success-msg"> - *사용가능한 닉네임입니다</span></label>
			       	 <input type="text" id="nickname" name="nickname" placeholder="닉네임 입력" 
			       	 	data-rule="^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]+$"
			       	 	data-error="INVALID_NICK">
			      </div>
			      
				  <div class="field">
				    <label>이메일  <span class="error-msg"> - *중복된 이메일입니다</span><span class="success-msg"> - *사용가능한 이메일입니다</span></label>
					 <input type="text" id= "email" name="email" placeholder="이메일 입력" 
					 	data-rule="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"
					 	data-error="INVALID_EMAIL">
				  </div>
				  
				  <div class="ui button" id="btn_join" tabindex="0">가입하기</div>
			</form>
		</div>
		</div>
	<!--하단고정된 푸터 -->
	<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>
</html>
