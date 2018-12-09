<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function delPost(pnum){
		
		var f = document.getElementById("delForm");
		f.submit();
		
	
	}
</script>
</head>
<body>
<c:if test="${not empty postView}">
	<h3><c:out value="${postView.title}"/></h3>
	<p><c:out value="${postView.content}"/></p>
	<form id="delForm" action="${pageContext.request.contextPath}/delete" method="post">
		<input type="hidden"  value="${postView.seq}" name="pnum"></input>
		<button type="submit">삭제</button>
	</form>
</c:if>
<c:if test="${empty postView}">
	글이없음.
</c:if>




</body>
</html>