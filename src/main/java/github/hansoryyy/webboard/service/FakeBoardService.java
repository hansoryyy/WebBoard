/*package github.hansoryyy.webboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import github.hansoryyy.webboard.dto.PostDTO;
*//**
 * IXXX
 *    -> IXXXImpl
 *    
 * interface XXXDao
 *     -> Xml
 * @author Kizuna
 *
 *//*

public class FakeBoardService implements IBoardService {

	List<PostDTO> fakes = new ArrayList<>();
	{
		fakes.add(new PostDTO(1000, "한글되냐?", "content1", "fffff@naver.com" ));
		fakes.add(new PostDTO(1001, "title2", "content2", "yesk@naver.com" ));
		fakes.add(new PostDTO(1002, "gasdkfdsk ", "content2", "yesk@naver.com" ));
		fakes.add(new PostDTO(1003, "gex333", "content2", "yesk@naver.com" ));
		fakes.add(new PostDTO(1004, "GGGG", "content2", "yesk@naver.com" ));
	}
	@Override
	public List<PostDTO> findAll() {

		return fakes;
	}

	@Override
	public PostDTO getContent(Integer pnum) {
		
		 * zerobase index : 
		 
		for(int i=0; i<fakes.size(); i++) { // [ 0, 1, 2, 3, 4]
			if(fakes.get(i).getSeq() == pnum) {
				return fakes.get(i);
			}
		}
		// 리스트 안을 다 찾아봤는데 pnum에 해당하는 길이 없음
		return null;
		
	}

	@Override
	public void delete(Integer pnum) {
		for(int i=0; i<fakes.size(); i++) { // [ 0, 1, 2, 3, 4]
			if(fakes.get(i).getSeq() == pnum) {
				fakes.remove(i);
			}
		}
		
	}
}
*/