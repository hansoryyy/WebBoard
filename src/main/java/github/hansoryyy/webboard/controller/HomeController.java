package github.hansoryyy.webboard.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import github.hansoryyy.webboard.dto.PostDTO;
import github.hansoryyy.webboard.service.IBoardService;

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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest req) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("ctxpath", req.getContextPath());
		
		return "home";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		List<PostDTO> posts = boardService.findAll();
		req.setAttribute("postings", posts);
		return "list";
	}
	
	//ctrl + alt + 아래방향키
	// Integer.parseInt("haha");
	// content/1228
	// http://localhost/webboard/content
	@RequestMapping(value = "/content/{pnum}", method = RequestMethod.GET)
	public String content(HttpServletRequest req, @PathVariable Integer pnum) {
		PostDTO content = boardService.getContent(pnum);
		req.setAttribute("content", content);
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
	}
	
}
