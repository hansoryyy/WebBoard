package github.hansoryyy.webboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object maxFileError(MaxUploadSizeExceededException e) {
		Map res = new HashMap();
		res.put("success", false);
		res.put("cause", "TOO_BIG");
		return res;
	}
	
	// FileUploadException
	// ework.web.multipart.MaxUploadSizeExceededException: Maximum upload size of 1048576 bytes exceeded; nested exception is org.apache.commons.fileupload.FileUploadBase$SizeLimitExceededException
	


}
