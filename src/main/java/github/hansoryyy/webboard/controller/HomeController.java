package github.hansoryyy.webboard.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import github.hansoryyy.webboard.dto.MemberDTO;
import github.hansoryyy.webboard.dto.PostDTO;
import github.hansoryyy.webboard.service.IBoardService;
import github.hansoryyy.webboard.service.IMemberService;
import github.hansoryyy.webboard.service.MemberServiceImpl;

/**
 * Handles requests for the application home page.
 * 
 * @Service(name="kkkk");
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject // @Autowired 
	private IBoardService boardService;
	
	@Inject // @Autowired 
	private IMemberService MemberService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		List<PostDTO> posts = boardService.selectPostList();
		req.setAttribute("postings", posts);
		return "home";
	}
	
	//ctrl + alt + 아래방향키
	// Integer.parseInt("haha");
	// content/1228
	// http://localhost/webboard/content
	
/*	@RequestMapping(value = "/content/{pnum}", method = RequestMethod.GET)
	public String content(HttpServletRequest req, @PathVariable Integer pnum) {
		PostDTO postView = boardService.postView(pnum);
		req.setAttribute("postView", postView);
		return "content";
	}
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String invalidRequest(HttpServletRequest req) {
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest req,  @RequestParam Integer pnum) {
		boardService.delete(pnum);
		
		return "redirect:/list";
	}*/
	
	@RequestMapping(value = "/member/join", method = RequestMethod.GET)
	public String join(HttpServletRequest req) {
		
		return "member/join";
	}
	
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login(HttpServletRequest req) {
		
		return "member/login";
	}
	
	@RequestMapping(value = "/member/loginAction", method = RequestMethod.POST)
	@ResponseBody
	public Object loginAction(HttpSession session, @RequestParam String loginId, @RequestParam String loginPasswd ){
		System.out.println(loginId + ": " + loginPasswd);
		MemberDTO dto = MemberService.loginAction(loginId, loginPasswd);

		Map<String, Object> res = new HashMap<>();
		if ( dto != null) {
			session.setAttribute("loginInfo", dto);			
			res.put("member", dto);
		} else {
			res.put("cause", "invalid");
		}
		
		res.put("success", dto != null);
		
		return res; // ddd.jsp
	}
	
	
	
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
}
