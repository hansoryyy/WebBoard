package github.hansoryyy.webboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import github.hansoryyy.webboard.dto.MemberDTO;
import github.hansoryyy.webboard.service.ILoginService;
import github.hansoryyy.webboard.service.IMemberService;

@Controller
public class LoginController {
	
	@Inject
	private ILoginService LoginService;
	
	
	@RequestMapping(value = "/member/loginAction", method = RequestMethod.POST)
	@ResponseBody
	public Object loginAction(HttpSession session, @RequestParam String loginId, @RequestParam String loginPasswd ){
		System.out.println(loginId + ": " + loginPasswd);
		MemberDTO dto = LoginService.loginAction(loginId, loginPasswd);
		Map<String, Object> res = new HashMap<>();
		
		if ( dto != null) {
			session.setAttribute("loginInfo", dto);			
			res.put("member", dto);
		} else {
			res.put("cause", "invalid");
		}
		
		res.put("success", dto != null);
		
		return res;
	}
	
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}

}
