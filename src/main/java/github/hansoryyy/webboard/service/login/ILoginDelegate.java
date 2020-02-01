package github.hansoryyy.webboard.service.login;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

public interface ILoginDelegate {
	/**
	 * redirect url 을 반환함
	 * 
	 * @return
	 */
	String accept(HttpSession session);
	
	void handleLogin(HttpSession session, Map param, Model model);
	
	void handleLogout();
	


}
