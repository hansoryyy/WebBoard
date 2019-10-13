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

}
