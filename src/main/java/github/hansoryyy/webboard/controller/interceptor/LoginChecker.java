package github.hansoryyy.webboard.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginChecker extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("[INTERCEPTOR] " + req.getRequestURI());
		return true;
		// return super.preHandle(req, res, handler);
	}

}
