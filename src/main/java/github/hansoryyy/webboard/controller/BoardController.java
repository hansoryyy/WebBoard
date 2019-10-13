package github.hansoryyy.webboard.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import github.hansoryyy.webboard.dao.BoardDAO;
import github.hansoryyy.webboard.dto.BoardDTO;
import github.hansoryyy.webboard.service.IBoardService;

@Controller 
public class BoardController {
	
	@Autowired IBoardService boardService;
	
	
	
	private static Logger log = LoggerFactory.getLogger(BoardController.class);
	
	
	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	public String boardList(HttpServletRequest req, Model model) {
		
		List<BoardDTO> list = boardService.selectBoardList();
		// model.addObject(attributeValue)
		model.addAttribute("boardList", list);
		return "board/boardList";
	}
	
	
	
	
	@RequestMapping(value="/board/boardWriteForm", method = RequestMethod.GET)
	public String boardWriteForm() {
		//TODO 로그인 기능 구현 다되면 로그인VO정보  writeFrom에 뿌려줘야함 ...		
		return "/board/boardWriteForm";
	}
	
	@RequestMapping(value="/board/doWrite", method = RequestMethod.POST)
	public String boardWriteForm(MultipartHttpServletRequest req) throws IOException {
		//TODO 로그인 기능 구현 다되면 로그인VO정보  writeFrom에 뿌려줘야함 ...	
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String email = req.getParameter("email");
		List<MultipartFile> files = req.getFiles("file");
		String ip = req.getRemoteAddr();
		
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContents(content);
		dto.setEmail(email);
		dto.setFiles(files);
		dto.setCreateIp(ip);
		
		System.out.println(title);
		System.out.println(content);
		
		boardService.insertBoard(dto);
		
		
		/* 이런거 필요없음
		Iterator<String> nameItr = req.getFileNames();
		Map<String, Object> params = req.getParameterMap();
		while (nameItr.hasNext()) {
			// req.getParameter("title");
			String nameAttr = nameItr.next(); //
			MultipartFile file = req.getFile(nameAttr);
			if (file.isEmpty() == false) {
                log.info("---------- file start ----------");
                log.info("name : " + file.getName());
                log.info("filename : " + file.getOriginalFilename());
                log.info("size : " + file.getSize());
                log.info("---------- file end ----------\n");
            }
		}
		*/
		
		return "redirect:/boardWriteForm";
	}
	
	@RequestMapping(value="/board/doWrite2", method = RequestMethod.POST)
	public String boardWriteForm(
			@RequestParam String title, 
			@RequestParam String content, 
			@RequestParam MultipartFile file) {
		//TODO 로그인 기능 구현 다되면 로그인VO정보  writeFrom에 뿌려줘야함 ...	
		System.out.println(title);
		System.out.println(content);
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		return "redirect:/board/boardWriteForm";
	}
	
	
	
}
