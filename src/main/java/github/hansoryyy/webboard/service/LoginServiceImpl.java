package github.hansoryyy.webboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.hansoryyy.webboard.dao.MemberDAO;
import github.hansoryyy.webboard.dto.MemberDTO;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired MemberDAO memberDAO;
	
	@Override
	public MemberDTO loginAction(String loginId, String loginPasswd) {
		MemberDTO dto = memberDAO.selectLoginInfo(loginId, loginPasswd);
		return dto;
	}
	
	@Override
	public MemberDTO loginOuterChannel(MemberDTO dto) {
		MemberDTO member = memberDAO.findMember(dto);
		if(member == null) {
			memberDAO.memberJoin(dto);
			member = dto;
			System.out.println("###네이버 회원으로 가입한 적이 없음 ");
		} else {
			;
			System.out.println("###네이버 회원으로  ");
		}
		return member;
	}

}
