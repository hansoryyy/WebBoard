package github.hansoryyy.webboard.controller.interceptor;

import java.lang.reflect.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import github.hansoryyy.webboard.dto.MemberDTO;

public class LoginChecker extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)throws Exception {
		System.out.println("[INTERCEPTOR] " + req.getRequestURI());
		
		String uri = strip(req); // /member/login
		if("/member/login".equals(uri) && hasLogin(req)) {
			res.sendRedirect("/");
			return false;
		}
		if ( "/member/join".equals(uri) && hasLogin(req) ) {
			res.sendRedirect("/");
			return false;
		}
		return true;
		// return super.preHandle(req, res, handler);
	}

	boolean hasLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("loginInfo");
		if(dto!=null) {
			return true;
		}
		return false;
	}
	
	String strip(HttpServletRequest req) {
		String fullUri = req.getRequestURI();
		String ctxpath = req.getContextPath();
		return fullUri.substring(ctxpath.length());
	}

}
