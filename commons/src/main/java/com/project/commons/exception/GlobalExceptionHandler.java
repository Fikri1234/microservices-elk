package com.project.commons.exception;

import com.project.commons.model.enums.MethodMessage;
import com.project.commons.model.response.ObjectApiResponse;
import com.project.commons.model.response.ObjectMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by user on 7:09 22/07/2024, 2024
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException(Exception ex) {

        ObjectMessageResponse msg = new ObjectMessageResponse();
        msg.setMessage(ex.getMessage());

        ObjectApiResponse dto = new ObjectApiResponse();
        dto.setStatus(MethodMessage.MSG_SOMETHING_WRONG.getStatus().getEn());
        dto.setMessage(MethodMessage.MSG_SOMETHING_WRONG.getEn());
        dto.setData(msg);

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
