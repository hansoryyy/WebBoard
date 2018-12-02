package github.hansoryyy.webboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.hansoryyy.webboard.dao.PostDAO;
import github.hansoryyy.webboard.dto.PostDTO;
@Service
public class BoardServiceImpl implements IBoardService {

	@Autowired PostDAO postDAO;
	
	
	@Override
	public List<PostDTO> findAll() {
		return postDAO.findAll();
	}

	@Override
	public PostDTO getContent(Integer pnum) {
		
		return postDAO.findPost(pnum);
	}

	@Override
	public void delete(Integer pnum) {
		// TODO Auto-generated method stub
		
	}

}
