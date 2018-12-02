<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>게시판이다</h3>
<table class="table">
<c:forEach var="p" items="${postings}">
	<tr>
		<td><a href="content/${p.seq}">${p.title}</a></td> 
		<td><a href="content.do?seq=${p.seq}&kw=감자">${p.title}</a></td>
		<td>${p.writer}</td>
	</tr>
</c:forEach>
</table>
</body>
</html>