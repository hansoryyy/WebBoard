package github.hansoryyy.webboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.hansoryyy.webboard.dao.MemberDAO;
import github.hansoryyy.webboard.dto.MemberDTO;
import github.hansoryyy.webboard.util.AES256Util;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired MemberDAO memberDAO;
	
	@Autowired AES256Util enc ;
	// =  new AES256Util("as838$(!9#ckasdk3e".toCharArray());
	
	@Override
	public void memberJoin(MemberDTO dto) {
		// String encryptionPw = AES256Util.encrypt(dto.getLoginPw());
		String pw = enc.encrypt(dto.getLoginPw());
		dto.setLoginPw(pw);
		memberDAO.memberJoin(dto);
	}

	@Override
	public int memberCheck(String prop, String value) {
		int cnt = memberDAO.checkMmember(prop, value );
		if(cnt>0) {
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public MemberDTO loginAction(String loginId, String loginPasswd) {
		MemberDTO dto = memberDAO.selectLoginInfo(loginId, loginPasswd);
		return dto;
		/*
		if(dto!=null) {
			
			return true;
		}else {
			
			return false;
		}
		*/
		
		
	}

}
