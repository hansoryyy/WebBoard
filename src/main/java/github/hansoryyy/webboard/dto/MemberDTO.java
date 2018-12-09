package github.hansoryyy.webboard.dto;
/**
 * loginId=yeori&
 * loginPw=asldkjldk&nickname=yeori&email=yeori.seo%40gmail.com
 * @author Kizuna
 *
 */
public class MemberDTO {
	private int seq;
	private String loginId;
	private String loginPw;
	private String nickname;
	private String email;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPw() {
		return loginPw;
	}
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [seq=" + seq + ", loginId=" + loginId + ", loginPw=" + loginPw + ", nickname=" + nickname
				+ ", email=" + email + "]";
	}
	
}
