package github.hansoryyy.webboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import github.hansoryyy.webboard.dto.MemberDTO;
import github.hansoryyy.webboard.service.IMemberService;

@Controller
public class MemberController {
	
	
	@Autowired
	private IMemberService memberService;
	
	@ResponseBody
	@RequestMapping(value="/member/memberJoin", method=RequestMethod.POST)
	public Object memberJoin(MemberDTO dto) throws JsonProcessingException {
		String smalLetterlPw = dto.getLoginPw().toLowerCase();	
		dto.setLoginPw(smalLetterlPw);//대소문자 구분하지 않음
		memberService.memberJoin(dto);
	
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put("user", dto);
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/member/memberCheck", method=RequestMethod.GET)
	public Object memberIdCheck(@RequestParam String prop, @RequestParam String value ) {
		
		int existId = memberService.memberCheck(prop, value);
		Map<String, Object> res = new HashMap<>();
		if(existId > 0) {
			res.put("existId", "DUP_VALUE" ); // {"existId": "INVALID_FORM"}
		}else {
			res.put("existId", "0");
		}
		return res;
	}
	
	
	

}
