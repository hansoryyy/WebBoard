

/*
 * Copyright 2008-2009 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package github.hansoryyy.webboard.util;

import java.util.Map;

/*import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;*/

/**
 * 게시판의 페이징을 도와주는 클래스<br/>
 * 
 * 
 * @author  : kdarkdev
 * @date    : 2014. 3. 12.
 * @version : 1.0
 */
public class PageInfo {
	
	/**
	 * DB조회전 페이징에 필요한 파라미터 초기화<br/><br/>
	 * 이 메서드는 기본적으로 프레임워크의 PageInfoHandlerMethodArgumentResolver에서 생성해서<br/>
	 * Controller의 파라미터로 받기때문에 별도로 객체 생성하지는 않는다.<br/><br/>
	 * 
	 * 만약 시스템 설정상에 기본적으로 설정되어있는 recordCountPerPage(페이지당 게시물 건수), <br/>
	 * pageSize(페이지 목록에 표현되는 페이지수)를 사용하지 않는다면 Controller에서 pageInfo.init(param)을 하기 전에 <br/>
	 * pageInfo.setRecordCountPerPage(10), pageInfo.setPageSize(10)과 같이 설정을 해줘야 한다. <br/><br/>
	 * 
	 * pageInfo.init() 실행후에는 db query시에 전달되야 하는 firstRecordIndex, lastRecordIndex가<br/>
	 * map에 넣어진다.<br/><br/>
	 * 
	 * <pre>
	 *  // Controller에서의 페이징 예제
	 *  public String itemView(ModelMap model, @RequestParam Map param, PageInfo pageInfo) {
	 *      // 아래와같은 set은 프레임워크의 PageInfoHandlerMethodArgumentResolver에서 처리해준다.
	 *      // 별도로 다른 값을 넣고 싶을때에만 사용하고 대부분은 pageInfo.init(param); 만 해도 된다.
	 *      // pageInfo.setRecordCountPerPage(5);
	 *      // pageInfo.setPageSize(15);
	 *      // pageInfo.setCurrentPageNo(param.get('depth2PageNo'));
	 *      
	 *      pageInfo.init(param);                                              // request parameter 정보에 페이징에 관련된 정보를 put한다.
     *      List itemList = itemService.selectItemList(pageParams);            // list조회
     *      model.addAttribute("itemList", itemList);                          // jsp에서 list를 그리기위해 set
     *      long itemListCount = itemService.selectItemListCount(pageParams);  // list의 총 갯수 (jsp에서 페이징을 위해)
     *      pageInfo.setTotalRecordCount(itemListCount);
     *      
     *      // pageInfo는 자동으로 request에 pageInfo라는 key로 setAttribute된다.
	 *  }
	 *  
	 * <pre>
	 *  // view에서 아래와 같이 jsp include한다
	 *  &lt;jsp:include page="/WEB-INF/views/include/pageInfo/pageInfo.jsp">
     *      &lt;jsp:param name="function" value="fnGoPage"/>
     *  &lt;/jsp:include>
	 * </pre>
	 * 
	 * @param  params {@code Map}
	 * @return
	 */
	public Map<String, Object> init(Map<String, Object> params) {
		params.put("firstRecordIndex",   this.getFirstRecordIndex());
		params.put("lastRecordIndex",    this.getLastRecordIndex());
		params.put("recordCountPerPage", this.getRecordCountPerPage());	
		return params;
	}

	/**
	 * 현재 페이지 번호 (필수)
	 */
	private long currentPageNo;
	
	/**
	 * 한 페이지당 게시되는 게시물 건 수 (필수)
	 */
	private long recordCountPerPage;
	
	/**
	 * 페이지 리스트에 게시되는 페이지 건수 (필수)
	 */
	private long pageSize;
	
	/**
	 * 전체 게시물 건 수 (필수)
	 */
	private long totalRecordCount;
	
	/**
	 * 한 페이지당 게시되는 게시물 건 수 get
	 * 
	 * @return
	 */
	public long getRecordCountPerPage() {
		return recordCountPerPage;
	}
	
	/**
	 * 한 페이지당 게시되는 게시물 건 수 set
	 * 
	 * @param recordCountPerPage
	 */
	public void setRecordCountPerPage(long recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	
	/**
	 * 페이지 리스트에 게시되는 페이지 건수 get
	 * 
	 * @return
	 */
	public long getPageSize() {
		return pageSize;
	}
	
	/**
	 * 페이지 리스트에 게시되는 페이지 건수 set
	 * 
	 * @param pageSize
	 */
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 현재 페이지 번호 get
	 * 
	 * @return
	 */
	public long getCurrentPageNo() {
		return currentPageNo;
	}
	
	/**
	 * 현재 페이지 번호 set
	 * 
	 * @param currentPageNo
	 */
	public void setCurrentPageNo(long currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	
	/**
	 * 전체 게시물 건수를 set하면서 아래와같은 view페이지에서 사용될 정보를 추가로 계산한다.<br/>
	 * <pre>
	 *  - totalPageCount (총 페이지 갯수)
	 *  - firstPageNoOnPageList (첫번째 페이지 번호)
	 *  - lastPageNoOnPageList (마지막 페이지 번호)
	 * </pre>
	 * 
	 * @param totalRecordCount
	 */
	public void setTotalRecordCount(long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
		
		// 총 페이지 갯수
		totalPageCount = ((getTotalRecordCount()-1)/getRecordCountPerPage()) + 1;
		
		// 첫번째 페이지 번호
		firstPageNoOnPageList = ((getCurrentPageNo()-1)/getPageSize())*getPageSize() + 1;
		
		// 마지막 페이지 번호
		lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;		
		if(lastPageNoOnPageList > getTotalPageCount()){
			lastPageNoOnPageList = getTotalPageCount();
		}
		
		// 페이지당 maxSeq => 게시물총갯수 - ((현재페이지-1) * 한 페이지당 게시되는 게시물 건 수)
		maxSeqPerPage = totalRecordCount - ((getCurrentPageNo()-1) * getRecordCountPerPage());
	}
	
	public long getTotalRecordCount() {
		return totalRecordCount;
	}
	
	/**
	 * Not Required Fields
	 * - 이 필드들은 Required Fields 값을 바탕으로 계산해서 정해지는 필드 값이다.
	 * 
	 * totalPageCount: 페이지 개수
	 * firstPageNoOnPageList : 페이지 리스트의 첫 페이지 번호
	 * lastPageNoOnPageList : 페이지 리스트의 마지막 페이지 번호
	 * firstRecordIndex : 페이징 SQL의 조건절에 사용되는 시작 rownum. 
	 * lastRecordIndex : 페이징 SQL의 조건절에 사용되는 마지막 rownum.
	 * maxSeqPerPage : 페이지당 maxSeq
	 */
	private long totalPageCount;
	private long firstPageNoOnPageList;
	private long lastPageNoOnPageList;
	private long firstRecordIndex;
	private long lastRecordIndex;
	private long maxSeqPerPage;
	
	public long getTotalPageCount() {
		return totalPageCount;
	}
	
	public long getFirstPageNo(){
		return 1;
	}
	
	public long getLastPageNo(){
		return getTotalPageCount();		
	}
	
	public long getFirstPageNoOnPageList() {
		return firstPageNoOnPageList;
	}
	
	public long getLastPageNoOnPageList() {		
		return lastPageNoOnPageList;
	}

	public long getFirstRecordIndex() {
		firstRecordIndex = (getCurrentPageNo() - 1) * getRecordCountPerPage();
		return firstRecordIndex;
	}

	public long getLastRecordIndex() {
		lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
		return lastRecordIndex;
	}
	
	public long getMaxSeqPerPage() {
		return maxSeqPerPage;
	}

	public void setMaxSeqPerPage(long maxSeqPerPage) {
		this.maxSeqPerPage = maxSeqPerPage;
	}

	/*
	 * @Override public String toString() { return
	 * ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); }
	 */
}
