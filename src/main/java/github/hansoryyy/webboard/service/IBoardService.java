package github.hansoryyy.webboard.service;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import github.hansoryyy.webboard.dto.BoardDTO;
import github.hansoryyy.webboard.dto.UpfilesDTO;


public interface IBoardService {
	
	void insertBoard(BoardDTO dto) throws IOException;

	List<BoardDTO> selectBoardList(Map param);

	long selectBoardListCount(Map param);

	BoardDTO selectBoardView(Map param);

	List<UpfilesDTO> selectUpfilesList(Map param);
	
	
}