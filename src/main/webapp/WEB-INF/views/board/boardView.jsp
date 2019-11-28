<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/include/incResource.jspf" %>
	<script>
		$(document).ready(function(){
			
		});
	
		function fnUpfilesDownload(upfilesNo){
			alert(upfilesNo + '  준비중입니다.');	
		}
	
	</script>
	<style>
		.grid i.icon.thumbs.up.outline:before{font-size: 40px;}
		.grid i.icon.thumbs.down.outline:before{font-size: 40px;}
		.grid i.icon.bookmark.outline:before{font-size: 40px;}
	</style>
</head>
<body>
	<!--상단 고정된 헤더 -->
	<%@include file="/WEB-INF/views/include/header.jspf" %>
	
	<!--중앙 몸통 시작 -->
	<div class="main-content">
	<h1 class="ui header">게시글 상세 내역</h1>
	<div class="ui divider"></div>
		<div class="ui segment">
  			<h2 class="ui left floated header">${boardInfo.title}</h2>&nbsp;&nbsp;
  			<span style="float: left;padding-top: 10px;">
  				<i class="clock outline icon"></i> ${boardInfo.writeDt}
  			</span>
  			<span style="float: right;padding-top: 10px;">
  				<i class="comment alternate icon" title="댓글수 "></i>10&nbsp;&nbsp;
	  			<i class="eye icon" title="조회수"></i>${boardInfo.hits}&nbsp;&nbsp;
	  			<i class="thumbs up outline icon" title="좋아요" ></i>${boardInfo.likes}&nbsp;&nbsp;
	  			<i class="thumbs down outline icon" title="싫어요"></i>${boardInfo.hates}
  			</span>
  			<c:if test="${not empty upfilesList}">
	  			<div class="ui clearing divider"></div>
	  			<c:forEach var="upfilesList" items="${upfilesList}">
	  				<i class="paperclip icon"></i>&emsp;<span style="margin-right: 10px;"><a href="#link" onclick="javascript:fnUpfilesDownload('${upfilesList.upfilesNo}');">${upfilesList.originFilename}</a></span><br/>
	  			</c:forEach>
	  		</c:if>
  			
  			<div class="ui clearing divider"></div>
  				<div class="ui internally celled grid" style="min-height: 500px;">
  					<div class="row">
  						<div class="fifteen wide column">
  							<c:out value="${boardInfo.contents}" escapeXml="false"/>
  						</div>
<%--   					<div class="one wide column" style="text-align:center;">
  							<div>
	      						<p><i class="thumbs up outline icon" title="좋아요" ></i></p>
	      						<p style="margin-bottom:20px;font-size:40px;height:60px;padding-left: 12px;color:rgba(0, 0, 0, 0.4)"><c:set var="vote" value="${boardInfo.likes - boardInfo.hates}"/>${vote}</p>
	      						<p style="margin-bottom: 30px;"><i class="thumbs down outline icon" title="싫어요"></i></p>
	      						<p><i class="bookmark outline icon"></i></p>
 							</div>   					
    					</div> --%>
  					</div>
  				</div>
  			<div class="ui clearing divider"></div>
  			<p></p>
		</div>
		
  
    <div class="three wide column">
      <img>
    </div>
    <div class="ten wide column">
      <p></p>
    </div>
    <div class="three wide column">
      <img>
    </div>
  </div>
</div>
	</div>
	<!--하단고정된 푸터 -->
	<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>
</html>
