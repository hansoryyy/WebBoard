package github.hansoryyy.webboard.service;

import github.hansoryyy.webboard.dto.MemberDTO;

public interface IMemberService {

	public void memberJoin(MemberDTO dto);

	public int memberCheck(String prop, String value);
	
	public MemberDTO loginAction(String loginId, String loginPasswd);
}
