package github.hansoryyy.webboard.service;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import github.hansoryyy.webboard.dao.BoardDAO;
import github.hansoryyy.webboard.dto.BoardDTO;
import github.hansoryyy.webboard.dto.UpfilesDTO;
@Service
public class BoardServiceImpl implements IBoardService {

	@Autowired BoardDAO boardDAO;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void insertBoard(BoardDTO dto) throws IOException {
		// 1. 디비에 글 내용을 기록함 (
		int boardNo = boardDAO.insertBoard(dto);	//insert된 board_info pk값을 return

		List<String[]> files = dto.getFiles();
		
		for(int i=0; i<files.size(); i++) {
			String[] file = files.get(i);

				
				// 2.1. upfiles 테이블에 업로드 파일 정보를 저장함
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss_SSS");
				Date time = new Date();
				String currentTime = format.format(time);
				Map tmpMap = new HashMap();
				tmpMap.put("genFilename", file[0]);
				tmpMap.put("originFilename", file[1]);
				tmpMap.put("boardNo", boardNo);
				tmpMap.put("createIp", dto.getCreateIp());
				boardDAO.insertUpFiles(tmpMap);
				
//				File destFile = new File(rootPath, (String)tmpMap.get("genFilename"));
//				FileOutputStream fos = new FileOutputStream(destFile);
//				IOUtils.copy(file.getInputStream(), fos);

		}
		// FileUtils.copyFile(srcFile, destFile);
	}
	
	@Override
	public List<BoardDTO> selectBoardList(Map param) {
		return boardDAO.selectBoardList(param);
	}
	
	@Override
	public long selectBoardListCount(Map param) {
		return boardDAO.selectBoardListCount(param);
	}
	
	@Override
	public BoardDTO selectBoardView(Map param) {
		int res = boardDAO.updateBoardHits(param);
		return boardDAO.selectBoardView(param);
	}
	
	@Override
	public List<UpfilesDTO> selectUpfilesList(Map param) {
		return boardDAO.selectUpfilesList(param);
	}
	

}
