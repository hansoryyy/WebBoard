package github.hansoryyy.webboard.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import github.hansoryyy.webboard.dto.PostDTO;

@Repository
public class PostDAO {
	
	// @Resource(name="session") SqlSession session; // 등록된 bean에서 id값으로 찾기
	@Autowired SqlSession session;	//SqlSession 타입으로 찾음

	public List<PostDTO> findAll() {
		return session.selectList("PostMapper.findAll");	//mapper namespace, query id
	}

	public PostDTO findPost(Integer pnum) {
		return session.selectOne("PostMapper.findPost", pnum);
	}
	
	
	
	
	
	

	
}
