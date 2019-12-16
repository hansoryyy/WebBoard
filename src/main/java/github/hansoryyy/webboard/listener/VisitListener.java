package github.hansoryyy.webboard.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import github.hansoryyy.webboard.dao.VisitDAO;
import github.hansoryyy.webboard.dto.VisitDTO;

public class VisitListener implements HttpSessionListener {
	
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session =  se.getSession();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		VisitDAO visitDAO = (VisitDAO) wac.getBean("visitDAO");
		
		long totalVisitCount = 0;
		long todayVisitCount = 0;
	
		VisitDTO dto = new VisitDTO();
		dto.setVisitIp(req.getRemoteAddr());
		dto.setVisitAgent(req.getHeader("User-Agent"));
		dto.setVisitRefer(req.getHeader("referer"));
		
		try {
			visitDAO.insertVisitor(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		totalVisitCount = visitDAO.selectTotalVisitCount();
		todayVisitCount = visitDAO.selectTodayVisitCount();
		
//		session.setAttribute("totalVisitCount", Integer.toString(totalVisitCount));
//		session.setAttribute("todayVisitCount", Integer.toString(todayVisitCount));
		
		session.setAttribute("totalVisitCount", totalVisitCount);
		session.setAttribute("todayVisitCount", todayVisitCount);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

}
