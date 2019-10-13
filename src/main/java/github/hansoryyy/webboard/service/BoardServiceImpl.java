package github.hansoryyy.webboard.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import github.hansoryyy.webboard.dao.BoardDAO;
import github.hansoryyy.webboard.dto.BoardDTO;
import github.hansoryyy.webboard.dto.PostDTO;
@Service
public class BoardServiceImpl implements IBoardService {

	@Autowired BoardDAO boardDao;
	
	String rootPath = "C:\\Users\\kizuna\\Documents\\uproot";
	
	
	
	@Override
	public List<PostDTO> selectPostList() {
		return boardDao.selectPostList();
	}

	@Override
	public PostDTO postView(Integer pnum) {
		
		return boardDao.postView(pnum);
	}

	@Override
	public void delete(Integer pnum) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void insertBoard(BoardDTO dto) throws IOException {
		// 1. 디비에 글 내용을 기록함
		boardDao.insertBoard(dto);
		int boardNo = dto.getBoardNo();
		
		// 2. 파일을 저장함 - 로컬디렉토리 or 외부 storage 업체 api 이용
		
		// 2.1. upfiles 테이블에 업로드 파일 정보를 저장함
		
		// 2.2. 실제 디스크레 저장함
		
		// 2.2.1. 새로운 파일 이름을 생성해야 합니다(이름 충돌 방지)
		List<MultipartFile> files = dto.getFiles();
		for(int i=0; i<files.size(); i++) {
			MultipartFile file = files.get(i);
			if(! file.isEmpty() ){
				File destFile = new File(rootPath, file.getOriginalFilename());
				FileOutputStream fos = new FileOutputStream(destFile);
				IOUtils.copy(file.getInputStream(), fos);				
			}
		}
		// FileUtils.copyFile(srcFile, destFile);
	}
	
	@Override
	public List<BoardDTO> selectBoardList() {
		return boardDao.selectBoardList();
	}
	

}
