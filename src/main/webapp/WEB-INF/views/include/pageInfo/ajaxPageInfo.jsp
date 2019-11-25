<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
		
		 <!--  변수 설정 -->
			  <c:set var="reqUrl" value="${requestScope['javax.servlet.forward.request_uri']}" />	
			  <c:set var="currentPageNo" value="${pageInfo.currentPageNo == 1?1:pageInfo.currentPageNo-1}"/>
			  <c:set var="function" value="${param.function==null?'fnGoPage':param.function}" />
		 <!--//변수 설정 -->


			<div class="ui pagination menu">
			  
<!-- 		<a class="item">
			    &lt;&lt;
			  </a> -->
			  

			  
			  <a class="item" onclick="${function}('${reqUrl}', ${currentPageNo})" title="${currentPageNo}페이지">
			    &lt;
			  </a>
				  
				<c:forEach var="i" begin="${pageInfo.firstPageNoOnPageList}" end="${pageInfo.lastPageNoOnPageList}" step="1">  
					<c:choose>
						<c:when test="${i == pageInfo.currentPageNo}">
						  	<a class="active item" title="${i}페이지">${i}</a>
						 </c:when>
						 <c:otherwise>
						 	<a class="item" onclick="${function}('${reqUrl}', ${i})" title="${i}페이지">${i}</a>
						 </c:otherwise>
					 </c:choose>
			 	 </c:forEach>
			 
			  <c:set var="currentPageNo" value="${pageInfo.currentPageNo == pageInfo.totalPageCount?pageInfo.totalPageCount:pageInfo.currentPageNo+1}"/>
			  
			  <a class="item" onclick="${function}('${reqUrl}', ${currentPageNo})" title="${currentPageNo}페이지" >
			  	&gt;
			  </a>
			  
<!-- 		  <a class="item">
			    &gt;&gt;
			  </a> -->
			  
			  
			</div>
			
			
			
			