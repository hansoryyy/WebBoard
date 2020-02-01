package github.hansoryyy.webboard.controller;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import github.hansoryyy.webboard.dto.MemberDTO;
import github.hansoryyy.webboard.service.ILoginService;
import github.hansoryyy.webboard.service.IMemberService;
import github.hansoryyy.webboard.service.login.ILoginDelegate;

@Controller
public class LoginController {
	
	@Inject
	private ILoginService LoginService;
	
	@Inject
	private ILoginDelegate naverLogin;
	
	@Inject
	private ILoginDelegate daum;

	
	@RequestMapping(value="/login/loginAction", method = RequestMethod.POST)
	@ResponseBody
	public Object loginAction(HttpSession session, @RequestParam String loginId, @RequestParam String loginPasswd){
		// HttpSession session = req.getSession();
		/*
		 *  String jsessionId= req.geCoookie().find("asdkfljaskfjeksl2323");
		 *  sessionHolder.findSEssion(jsessionId);
		 *  if ( null ) {
		 *     new HttpSession(...);
		 */
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

	@RequestMapping(value = "/login/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		MemberDTO dto = (MemberDTO) session.getAttribute("loginInfo");
		if(dto!=null) {
			if(dto.getJoinChannel().equals("naver")) {
				naverLogin.handleLogout();
			}
			session.removeAttribute("loginInfo");
			
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login/social/{channel}", method = RequestMethod.GET)
	public String naverLogin(@PathVariable String channel, HttpSession session){
		String apiURL = "";
		
		if(channel.equals("naver") ){
			apiURL = naverLogin.accept(session);
		}
	    
	    return "redirect:"+apiURL;
	}
	
	@RequestMapping(value = "/login/social/callback/{channel}")
	public Object naverLoginCallback(@PathVariable String channel, HttpSession session, Model model, @RequestParam Map param) {
		String callbackPage ="";
		
		if(channel.equals("naver")){
			naverLogin.handleLogin(session, param, model);	
		}
		
		
		callbackPage = channel + "LoginCallbackPage";
		return "/login/social/callback/"+callbackPage;
	}
	
}
