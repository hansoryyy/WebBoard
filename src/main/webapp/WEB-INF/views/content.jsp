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
<c:if test="${not empty content}">
	<h3><c:out value="${content.title}"/></h3>
	<p><c:out value="${content.content}"/></p>
	<form id="delForm" action="${pageContext.request.contextPath}/delete" method="post">
		<input type="hidden"  value="${content.seq}" name="pnum"></input>
		<button type="submit">삭제</button>
	</form>
</c:if>
<c:if test="${empty content}">
	글이없음.
</c:if>




</body>
</html>