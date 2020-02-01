package github.hansoryyy.webboard.service.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import github.hansoryyy.webboard.dto.MemberDTO;
import github.hansoryyy.webboard.service.ILoginService;
import github.hansoryyy.webboard.util.HttpComponents;
import net.minidev.json.JSONObject;

@Service
public class NaverLoginService implements ILoginDelegate {
	

	@Value("${naver.clientId}") 
	private String naverClientId;
	
	@Value("${naver.clientSecret}") 
	private String naverClientSecret;
	
	@Value("${naver.login.redirectUri}") 
	private String naverLoginRedirectUri;
	
	@Inject
	private ILoginService LoginService;
	
	@Override
	public String accept(HttpSession session) {
		
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + naverClientId;
		
		String encodingUri = "";
		try {
			encodingUri=URLEncoder.encode(naverLoginRedirectUri+"/login/social/callback/naver","UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		apiURL += "&redirect_uri=" + encodingUri;
		
		SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    apiURL += "&state=" + state;
	    session.setAttribute("naverState", state);
	    
	    return apiURL;
	}
	
	@Override
	public void handleLogin(HttpSession session, Map param, Model model) {
		// TODO Auto-generated method stub
		// naverClientId
				String resultNaverState = (String)param.get("state");
				String resultNaverCode =  (String)param.get("code");
				String sessionNaverState = (String) session.getAttribute("naverState");
				String redirectURI = "";
				try {
					redirectURI = URLEncoder.encode(naverLoginRedirectUri, "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				 
			    if(resultNaverState.equals(sessionNaverState)) {
			    	String _url = "";
			    	_url += "https://nid.naver.com/oauth2.0/token?";
			    	_url += "&client_id=" + naverClientId;
			    	_url += "&client_secret=" + naverClientSecret;
			    	_url += "&grant_type=authorization_code";
			    	_url += "&code=" + resultNaverCode;
			    	_url += "&state=" + resultNaverState;
			    	
			    	String resJsonString = HttpComponents.post(_url);
			    	
			    	ObjectMapper mapper = new ObjectMapper();
			    	Map<String, String> resMap = null;
					try {
						resMap = mapper.readValue(resJsonString, Map.class);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					System.out.println("https://nid.naver.com/oauth2.0/token? :\n " + resMap);
					
					String accessToken = resMap.get("access_token");
					String refreshToken = resMap.get("refresh_token");
			    	String header = "Bearer "+ accessToken;
			    	String apiURL = "https://openapi.naver.com/v1/nid/me";
			    	
			        try {
				            URL url = new URL(apiURL);
				            HttpURLConnection con = (HttpURLConnection)url.openConnection();
				            con.setRequestMethod("GET");
				            con.setRequestProperty("Authorization", header);
				            int responseCode = con.getResponseCode();
				            BufferedReader br;
				            if(responseCode==200) { // 정상 호출
				              br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				            } else {  // 에러 발생
				              br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				            }
				            String inputLine;
				            StringBuffer res = new StringBuffer(); // stringbuffer -> stringBuilder 
				            while ((inputLine = br.readLine()) != null) {
				              res.append(inputLine);
				            }
				            br.close();
				            System.out.println("https://openapi.naver.com/v1/nid/me :\n " + res.toString());
				            
				            
							ObjectMapper _mapper = new ObjectMapper();
							Map<String, Object> respMap = new HashMap<>();
							respMap = _mapper.readValue(res.toString(), new TypeReference<Map>(){});
				            
				            if( ((String)respMap.get("resultcode")).equals("00") ) {
				            	String id = (String)(((Map)respMap.get("response")).get("id"));
				            	String email = (String)(((Map)respMap.get("response")).get("email"));
				            	String name = (String)(((Map)respMap.get("response")).get("name"));
				            	
				            	MemberDTO dto = new MemberDTO();
				            	dto.setLoginId("naver"+id);
				            	dto.setLoginPw(id);
				            	dto.setEmail(email);
				            	dto.setNickname(name);
				            	dto.setJoinChannel("naver");
				            	dto.setAccessToken(accessToken);
				            	dto.setRefreshToken(refreshToken);
				            	
				            	dto = LoginService.loginOuterChannel(dto);
				            	session.setAttribute("loginInfo", dto);

				            	model.addAttribute("success", "true");
				            	model.addAttribute("message","네이버 아이디로 로그인 되었습니다.");			            	
				            }
 
			          } catch (Exception e) {
			        	  model.addAttribute("success","false");
			        	  model.addAttribute("message","네이버로그인 연동에 실패하였습니다.");
			          }
			        
			        
			    }
	}
	
	@Override
	public void handleLogout() {
//			https://nid.naver.com/oauth2.0/token?
//			grant_type=delete
//			&client_id=jyvqXeaVOVmV
//			&client_secret=527300A0_COq1_XV33cf
//			&access_token=c8ceMEJisO4Se7uGCEYKK1p52L93bHXLnaoETis9YzjfnorlQwEisqemfpKHUq2gY
//			&service_provider=NAVER
		
			String url = "";
			url+= "https://nid.naver.com/oauth2.0/token?";
			url+= "grant_type=delete";
			url += "&client_id=" + naverClientId;
			url += "&client_secret=" + naverClientSecret;
			url += "&access_token=";
			url += "&service_provider=NAVER";

	}
	


}
