<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<%@include file="/WEB-INF/views/include/incResource.jspf" %>
	
	<script type="text/javascript">
		var success = "${success}";
		if(success=="true"){		
			alert("네이버 아이디로 로그인 되었습니다");
			opener.window.location = "/";
			close();
		}else{
			alert("네이버 로그인  연동에 실패하였습니다.");
			opener.window.location = "/";
			close();
		}
	</script>
</head>
</html>