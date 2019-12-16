package github.hansoryyy.webboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import github.hansoryyy.webboard.dto.BoardDTO;
import github.hansoryyy.webboard.dto.VisitDTO;
import github.hansoryyy.webboard.util.Util;


@Repository
public class VisitDAO {
	@Autowired SqlSession session;
	
    public void insertVisitor(VisitDTO dto) throws Exception{
         session.insert("VisitMapper.insertVisitor", dto);
    }

	public int selectTotalVisitCount() {
		 return session.selectOne("VisitMapper.selectTotalVisitCount");
	}
	
	public int selectTodayVisitCount() {
		return session.selectOne("VisitMapper.selectTodayVisitCount");
	}
}
