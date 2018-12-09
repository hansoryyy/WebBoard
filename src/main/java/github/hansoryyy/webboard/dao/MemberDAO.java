package github.hansoryyy.webboard.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import github.hansoryyy.webboard.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired SqlSession session;
	
	public void memberJoin(MemberDTO dto) {
		session.insert("memberJoin", dto);
	}

}
