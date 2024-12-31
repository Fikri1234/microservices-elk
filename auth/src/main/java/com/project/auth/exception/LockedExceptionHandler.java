package com.project.auth.exception;

import com.project.commons.model.enums.MethodMessage;
import com.project.commons.model.enums.StatusConstant;
import com.project.commons.model.response.ObjectApiResponse;
import com.project.commons.model.response.ObjectMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by user on Jul, 2024
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class LockedExceptionHandler extends LockedException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LockedExceptionHandler(String exception) {
		super(exception);
	}
	
	public LockedExceptionHandler(String exception, Throwable throwable) {
		super(exception, throwable);

		ObjectMessageResponse msg = new ObjectMessageResponse();
		msg.setMessage(throwable.getMessage());
		ObjectApiResponse dto = new ObjectApiResponse();
		dto.setStatus(MethodMessage.MSG_USER_UNAUTHORIZED.getStatus().getEn());
		dto.setMessage(MethodMessage.MSG_USER_UNAUTHORIZED.getEn());
		dto.setData(msg);
		new ResponseEntity<>(dto, HttpStatus.OK);
	}

}
