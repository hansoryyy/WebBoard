<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/include/incResource.jspf" %>
	
<script type="text/javascript">

	$(documnet).ready(function(){
		
	});
	
	function fnBoardWriteForm(){
		location.href = "/board/boardWriteForm";
	}
	
</script>
<style type="text/css">
	.ui.table {width: 100%;background: #FFFFFF;margin: 1em 0em;border: none;-webkit-box-shadow: none;box-shadow: none;border-radius: 0.28571429rem;text-align: left;color: rgba(0, 0, 0, 0.87);border-collapse: separate;border-spacing: 0px;}
</style>
</head>
<body>
	<!--상단 고정된 헤더 -->
	<%@include file="/WEB-INF/views/include/header.jspf" %>
	
	<!--중앙 몸통 시작 -->
	<div style="padding: 10px 10px 0px 10px;float: right;">
		<button class="ui blue button" onclick="javascript:fnBoardWriteForm();">글쓰기</button>
	</div>
	<div>
		<table id="example" class="ui celled table" style="width:100%; padding : 65px;">
        <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${boardList}">
            <tr>
                <td>Tiger Nixon</td>
                <td>System Architect</td>
                <td>Edinburgh</td>
                <td>61</td>
                <td>2011/04/25</td>
                <td>$320,800</td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </tfoot>
    </table>
</div>
	<!--하단고정된 푸터 -->
	<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>
</html>
