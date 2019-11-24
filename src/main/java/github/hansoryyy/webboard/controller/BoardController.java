package github.hansoryyy.webboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import github.hansoryyy.webboard.dto.BoardDTO;
import github.hansoryyy.webboard.dto.UpfilesDTO;
import github.hansoryyy.webboard.service.IBoardService;
import github.hansoryyy.webboard.util.PageInfo;
/**
 * 게시판 요청을 담당하는 컨트롤로
 * - 조회
 * - 글쓰기
 * - 삭제
 * 
 * @author kizuna
 *
 */
@Controller 
public class BoardController {
	
	@Autowired IBoardService boardService;
	
	private static Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Value("${img.root}")
	private String rootPath;
	
	
	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	public String boardList(Model model, @RequestParam Map param, PageInfo pageInfo) {
		pageInfo.setRecordCountPerPage(15);			//페이지당 row 갯수
		pageInfo.setCurrentPageNo(1);				//현재 페이지 번호
		pageInfo.setPageSize(5);					//하단 페이지 리스트에 표현되는 페이지 갯수
		pageInfo.init(param);
		
		//검색단어가 있을떄 
		String kw = (String)param.get("searchKw");
		if(kw != null && !kw.equals("")) {
			String[] searchKw = kw.split("\\s+");
			param.put("searchKw", searchKw);
		}
		
		long boardListCount = boardService.selectBoardListCount(param);
		pageInfo.setTotalRecordCount(boardListCount);
		
		List<BoardDTO> boardList = boardService.selectBoardList(param);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageInfo", pageInfo);
		
		System.out.println("img root: " + rootPath);
		
		return "board/boardList";
	}
	/**
	 * 
	 * @param param
	 * @param pageInfo
	 * @param currentPageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/board/boardAjaxList", method = RequestMethod.GET)
	public Map boardAjaxList(@RequestParam Map param, PageInfo pageInfo, @RequestParam long currentPageNo) {
		
		//Map param = new HashMap();
		pageInfo.setRecordCountPerPage(15);			//페이지당 row 갯수
		pageInfo.setCurrentPageNo(currentPageNo);	//현재 페이지 번호
		pageInfo.setPageSize(5);					//하단 페이지 리스트에 표현되는 페이지 갯수
		pageInfo.init(param);
		
		//검색단어가 있을떄 
		String kw = (String)param.get("searchKw");
		if(kw != null && !kw.equals("")) {
			String[] searchKw = kw.split("\\s+");
			param.put("searchKw", searchKw);
		}
		
		long boardAjaxListCount = boardService.selectBoardListCount(param);
		pageInfo.setTotalRecordCount(boardAjaxListCount);
		
		List<BoardDTO> boardAjaxList = boardService.selectBoardList(param);
		Map res = new HashMap();
		res.put("success",true);
		res.put("boardAjaxListCount",boardAjaxListCount);
		res.put("boardAjaxList", boardAjaxList);
		res.put("pageInfo", pageInfo);
		
		return res;
	}
	/**
	 * 
	 * @param model
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/board/boardView", method = RequestMethod.GET)
	public String boardView(Model model, @RequestParam Map param) {
		BoardDTO boardInfo = boardService.selectBoardView(param);
		model.addAttribute("boardInfo", boardInfo);
		List<UpfilesDTO> upfilesList = boardService.selectUpfilesList(param);
		if(upfilesList!=null && upfilesList.size()>0) {
			model.addAttribute("upfilesList", upfilesList);
		}
		return "/board/boardView";
	}

	
	@RequestMapping(value="/board/boardWriteForm", method = RequestMethod.GET)
	public String boardWriteForm() {
		//TODO 로그인 기능 구현 다되면 로그인VO정보  writeFrom에 뿌려줘야함 ...		`
		return "/board/boardWriteForm";
	}
	
	@ResponseBody
	@RequestMapping(value="/board/doWrite", method = RequestMethod.POST)
	public String boardWriteForm(MultipartHttpServletRequest req, HttpSession session) throws IOException {
		//TODO 로그인 기능 구현 다되면 로그인VO정보  writeFrom에 뿌려줘야함 ...	
		/*
		 * 
		 */
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String email = req.getParameter("email");
		// List<MultipartFile> files = req.getFiles("file");
		String ip = req.getRemoteAddr();
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContents(content);
		dto.setEmail(email);
		// dto.setFiles(files);
		dto.setCreateIp(ip);
		
		List<String[]> imgFiles = null;
		if(session.getAttribute("aFiles")!= null) {
			imgFiles = (List<String[]>) session.getAttribute("aFiles");
			dto.setFiles(imgFiles);
		}
		// dto.setImageFiles(imgFiles);
		
		boardService.insertBoard(dto);
		
		session.removeAttribute("aFiles");
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/board/tempFileUpload", method = RequestMethod.POST)
	public Object tempFileUpload(MultipartHttpServletRequest req, HttpSession session) throws IOException {
		MultipartFile file = req.getFile("file");
		Map res = new HashMap();
		// TODO 서비스 클래스로 빼냄 FileStorageService
		// 
		if(! file.isEmpty() ){
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss_SSS");
			Date time = new Date();
			
			String originName = file.getOriginalFilename();
			String ext = originName.substring(originName.lastIndexOf('.'));
			String currentTime = format.format(time);
			String genFileName = currentTime + (int)(1000000*Math.random()) + ext;
		
			
			// String rootPath = "C:\\Users\\kizuna\\Documents\\uproot";
			File destFile = new File(rootPath, genFileName);
			FileOutputStream fos = new FileOutputStream(destFile);
			IOUtils.copy(file.getInputStream(), fos);
			
			List<String[]> files = (List) session.getAttribute("aFiles");
			if(files == null) {
				files = new ArrayList<>();
				session.setAttribute("aFiles", files);
			}
			
			files.add(new String[] {genFileName, originName});
			res.put("success", true);
			
			String imgUrl = req.getContextPath() + "/board/upimg/" + genFileName;
			res.put("img_url", imgUrl);
			res.put("length", destFile.length());
			res.put("fileId", genFileName);
		} else {
			res.put("success", false);
			res.put("cause", "EMPTY_IMG");
			
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/board/tempFileDelete", method = RequestMethod.POST)
	public Object tempFileDelete(HttpServletRequest req, HttpSession session) throws IOException {
		Map res = new HashMap();
		String fileId = req.getParameter("fileId");
		
		List<String[]> files = (List) session.getAttribute("aFiles");
		if(files != null) {
			for(int i=0; i< files.size(); i++) {
				String[] elem = files.get(i);
				if(elem[i].equals(fileId)) {
					files.remove(i);
				}
			}			
			res.put("success", true);
		}else {
			res.put("success", false);
		}
		return res;
	}
	
	@RequestMapping(value="/board/upimg/{imgpath:.+}", method = RequestMethod.GET)
	public void boardWriteForm(@PathVariable String imgpath, HttpServletResponse response) throws IOException {
		OutputStream out = response.getOutputStream();				// 브라우저에 보낼 출력 스트림
		
		File filepath = new File(rootPath, imgpath);
		if (filepath.exists()) {
			// 1. content-type
			int fsize = (int)filepath.length(); // 23,749
			response.setContentLength(fsize);
			String ext = imgpath.substring(imgpath.lastIndexOf('.')+1); // abc.jpg
			// 2. content type
			String type = "image/" + ext;
			response.setContentType(type);
			
			// 3. sending image data ....
			FileInputStream fin = new FileInputStream(filepath);	//서버디스크에 있는 파일으 읽어드림
			IOUtils.copy(fin, out);

		} else {
			response.sendError(404);
		}
	}
		
}
