package github.hansoryyy.webboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.hansoryyy.webboard.dao.MemberDAO;
import github.hansoryyy.webboard.dto.MemberDTO;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired MemberDAO memberDAO;
	
	@Override
	public void memberJoin(MemberDTO dto) {
		memberDAO.memberJoin(dto);
	}

	@Override
	public int memberCheck(String prop, String value) {
		int cnt = memberDAO.memberCheck(prop, value );
		if(cnt>0) {
			return 1;
		}else {
			return 0;
		}
	}


}
