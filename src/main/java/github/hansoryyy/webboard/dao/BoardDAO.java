package github.hansoryyy.webboard.dao;

import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import github.hansoryyy.webboard.dto.BoardDTO;
import github.hansoryyy.webboard.dto.UpfilesDTO;

@Repository
public class BoardDAO {
	
 // @Resource(name="session") SqlSession session; // 등록된 bean에서 id값으로 찾기
	@Autowired SqlSession session;	//SqlSession 타입으로 찾음


	public int insertBoard(BoardDTO dto) {
		session.insert("BoardMapper.insertBoard", dto);
		int boardNo = dto.getBoardNo();
		return boardNo;
	}
	
	public List<BoardDTO> selectBoardList(Map param) {
		 return session.selectList("BoardMapper.selectBoardList", param);
	}

	public void insertUpFiles(Map param) {
		session.insert("BoardMapper.insertUpFiles", param);
	}

	public long selectBoardListCount(Map param) {
		long count = session.selectOne("BoardMapper.selectBoardListCount", param);
		return count;
	}

	public BoardDTO selectBoardView(Map param) {
		 return session.selectOne("BoardMapper.selectBoardView", param);
	}
	
	public List<UpfilesDTO> selectUpfilesList(Map param){
		return session.selectList("BoardMapper.selectUpfilesList", param);
	}

	public int updateBoardHits(Map param) {
		return session.update("BoardMapper.updateBoardHits", param);
	}
		
}
