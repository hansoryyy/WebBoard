package github.hansoryyy.webboard.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import github.hansoryyy.webboard.dto.MemberDTO;
import github.hansoryyy.webboard.util.Util;

@Repository
public class MemberDAO {
	
	@Autowired SqlSession session;
	
	public void memberJoin(MemberDTO dto) {
		session.insert("memberJoin", dto);
	}

	public int memberCheck(String prop, String value) {
		Map<String, Object> param = new HashMap<>();
		param.put("prop", Util.camelToSnake(prop)); // lginId -> login_id
		param.put("value", value);
		int cnt = session.selectOne("memberCheck", param);
		return cnt;
	}

}
