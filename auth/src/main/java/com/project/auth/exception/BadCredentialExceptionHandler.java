package com.project.auth.exception;

import com.project.commons.model.enums.StatusConstant;
import com.project.commons.model.response.ObjectApiResponse;
import com.project.commons.model.response.ObjectMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by user on Jul, 2024
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class BadCredentialExceptionHandler extends BadCredentialsException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadCredentialExceptionHandler(String exception) {
		super(exception);
	}
	
//	public BadCredentialExceptionHandler(String exception, Throwable cause) {
//		super(exception, cause);
//
//		ObjectMessageResponse msg = new ObjectMessageResponse();
//		msg.setMessage(exception);
//		ObjectApiResponse dto = new ObjectApiResponse();
//		dto.setCode(StatusConstant.FAILED.getStatus());
//		dto.setMessage(StatusConstant.FAILED.getMsg());
//		dto.setData(msg);
//		new ResponseEntity<>(dto, HttpStatus.OK);
//	}

}
