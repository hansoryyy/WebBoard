package github.hansoryyy.webboard.service;

import github.hansoryyy.webboard.dto.MemberDTO;

public interface ILoginService {
	public MemberDTO loginAction(String loginId, String loginPasswd);
}
