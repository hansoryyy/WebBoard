package github.hansoryyy.webboard.service;

import java.util.List;

import github.hansoryyy.webboard.dto.PostDTO;
/**
 * 
 * controller
 *    -> service
 *       IXXXService
 *         // XXXServlceImpl
 *         // XXXServlceImpl2
 *        -> dao    --------------------------> db
 *
 * @author Kizuna
 *
 */
public interface IBoardService {
    /**
     * 	
     * @return
     */
	List<PostDTO> selectPostList();
	
	PostDTO postView(Integer pnum);
	/*
	void insert(PostDTO p );
	
	void update(PostDTO p) ;
	
	void delete(PostDTO p);
	*/
	/**		'/**' 엔터  이렇게하면됌
	 * 주어진 PK에 해당하는 글을 반환
	 * @param pnum
	 * @return
	 */
	
	/**
	 * 글삭제
	 * @param pnum
	 */
	void delete(Integer pnum);
}