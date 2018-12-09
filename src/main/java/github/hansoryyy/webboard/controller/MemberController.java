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
		
		memberService.memberJoin(dto);
		/*
		ObjectMapper om = new ObjectMapper();
		
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		
		String json = om.writeValueAsString(res); // {"succcess", true}
		return json;
		*/
		
		Map<String, Object> res = new HashMap<>();
		res.put("success", true);
		res.put("user", dto);
		
		return res;
		
		// ["dkdkd" ,"kwekk " , , ]
	}
	

}
