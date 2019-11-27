<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/include/incResource.jspf" %>
	
<script type="text/javascript">

	$(document).ready(function(){
		var searchType = '${param.searchType}'== ''? 'all' : "${param.searchType}";
		$(".dropdown").val(searchType).prop("selected", true);
	});
	
	function fnBoardView(boardNo){
		location.href="/board/boardView?boardNo=" + boardNo
	}
	
	function fnSerachKw(){
		var searchType = $(".searchForm select option:selected").val();
		$("#searchType").val(searchType);
		$("#searchForm").submit(); 
	}
	
	function fnBoardWriteForm(){
		location.href = "/board/boardWriteForm";
	}
	
	function fnGoPageAjax(url, currentPageNo) {
			var searchType = $(".searchForm select option:selected").val();
			var searchKw = $("#searchKw").val();
			
			$.ajax({
					url : '/board/boardAjaxList',
					data : 'currentPageNo='+currentPageNo+'&searchType='+searchType+'&searchKw='+searchKw,
					success : function (res) {
						var html="";
						var cnt = 0;
						for(cnt ; cnt < res.boardAjaxList.length;cnt++){
								
							html += '<tr>';
							html += '<td><a href="javascript:fnBoardView('+res.boardAjaxList[cnt].boardNo +')">' + res.boardAjaxList[cnt].title + '</a></td>';
							html += '<td>' + res.boardAjaxList[cnt].writer + '</td>';
							html += '<td style="text-align:center">' + res.boardAjaxList[cnt].hits + '</td>';
							html += '<td style="text-align:center">' + res.boardAjaxList[cnt].writeDt + '</td>';
							html += '<td style="text-align:center">' + String(res.boardAjaxList[cnt].likes - res.boardAjaxList[cnt].hates) + '</td>';
							html += '</tr>';
						}
	
						if(cnt!= 0){
							$("tbody").html(html);
						}
						html ="";
						var prevPageNo = currentPageNo-1;
						var nextPageNo = currentPageNo+1;
	
						if(prevPageNo ==0){
							prevPageNo = 1;
						}
						if(nextPageNo > res.pageInfo.totalPageCount){
							nextPageNo = res.pageInfo.totalPageCount;
						}	
						html ='<div class="ui pagination menu">';
						html += '<a class="item"  onclick=fnGoPageAjax(\"tmp\",'+prevPageNo+',\"currentPageNo\") title="'+prevPageNo+'페이지">';
						html += '&lt;'
						html += '</a>';
						
						for(var i =res.pageInfo.firstPageNoOnPageList; i <= res.pageInfo.lastPageNoOnPageList;  i++ ){
							if(i == currentPageNo){
								html +='<a  class="active item" title="${i}페이지">'+i+'</a>';
							}else{
								html += '<a  class="item" onclick=fnGoPageAjax(\"tmp\",'+i+',\"currentPageNo\") title="'+i+'페이지">'+i+'</a>';
							}
						}
						html += '<a class="item"  onclick= fnGoPageAjax(\"tmp\",'+nextPageNo+',\"currentPageNo\")  title="'+ nextPageNo +'페이지">';
						html += '&gt;' 
						html += '</a>';
						html += '</div>';
	
	
						if(cnt!= 0){
							$(".ajaxPaging").html(html);
							//itemReviewTable();
						}
	
					}	//success function 
			})
	}
	
	
	
</script>
<style type="text/css">
	.ui.table thead th {border:1px solid rgba(34, 36, 38, 0.1); text-align:center;}
</style>
</head>
<body>
	<!--상단 고정된 헤더 -->
	<%@include file="/WEB-INF/views/include/header.jspf" %>
	
	<!--중앙 몸통 시작 -->

	<div style="padding: 50px; min-height: 863px;">
		<h1 class="ui header">게시판 </h1>
		<div class="ui divider"></div>
		
		<!-- Search Area -->
		
		<div style="margin-bottom: 65px;">
		<button class="ui blue button" onclick="javascript:fnBoardWriteForm();" style="float:right">글쓰기</button>
		<form class="searchForm" id="searchForm" action="/board/boardList" method="get">
			<input type="hidden" id="searchType" name="searchType" class="searchType" value="all" /> 
		
			<div class="ui action input" style="float:left">
  				<input type="text" id="searchKw" name="searchKw" class="searchKw" value="<c:out value="${param.searchKw}"/>" placeholder="게시글 검색...">
  				<select class="ui compact selection dropdown">
   					 <option id="search_all" value="all" selected>전체</option>
    				 <option id="search_title" value="title">제목</option>
    				 <option id="search_writer" value="writer">작성자</option>
    				 <option id="search_contetns" value="contents">내용</option>
  				</select>
  				<div class="ui button" onClick="javascript:fnSerachKw();" >검색</div>	
			</div>
			
		</form>
		</div>
		<!-- //Search Area -->
		
		
		<table class="ui single line table" >
		 <colgroup>
			<col style="width:*" />
			<col style="width:15%" />
			<col style="width:10%" />
			<col style="width:15%" />
			<col style="width:10%" />
		  </colgroup>
		  <thead>
		    <tr>
		      <th style="border: 1px solid rgba(34, 36, 38, 0.1);">제목</th>
		      <th>작성자</th>
		      <th>조회수</th>
		      <th>작성날짜</th>
		      <th>추천</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:if test="${empty boardList}">
		  		<tr style="text-align:center;">
		  			<td colsapn="5"  >데이터가 없습니다 </td>
		  		</tr>
		  </c:if>
		   <c:if test="${not empty boardList}">
			  <c:forEach var="boardList" items="${boardList}" varStatus="status">
			  	<tr>
			      <td><a href="javascript:fnBoardView('${boardList.boardNo}');">${boardList.title}</a></td>
			      <td >${boardList.writer}</td>
			      <td style="text-align:center">${boardList.hits}</td>
			      <td style="text-align:center">${boardList.writeDt}</td>
			      <td style="text-align:center">${boardList.likes - boardList.hates}</td>
			    </tr>
			  </c:forEach>
		   </c:if>
		  </tbody>
		</table>
		
		<!-- pagination -->
		<div class="ajaxPaging" style="text-align:right">
			<jsp:include page="/WEB-INF/views/include/pageInfo/ajaxPageInfo.jsp">
				<jsp:param name="currentPageNoName" value="currentPageNo"/>
				<jsp:param name="function"          value="fnGoPageAjax"/>
				<jsp:param name="classNm"          value="bbs_foot_area2"/>
			</jsp:include>
		</div>
		<!-- //pagination -->
	</div>
	<!--하단고정된 푸터 -->
	<%@include file="/WEB-INF/views/include/footer.jspf" %>
</body>
</html>
