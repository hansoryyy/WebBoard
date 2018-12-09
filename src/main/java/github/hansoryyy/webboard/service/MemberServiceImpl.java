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


}
