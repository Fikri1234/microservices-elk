package com.project.commons.controller;


import com.project.commons.model.enums.MethodMessage;
import com.project.commons.model.response.ObjectApiResponse;
import com.project.commons.model.response.ObjectPagedApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

public class BaseController {
	
	public ObjectApiResponse responseApi(Object data) {

		ObjectApiResponse dto = new ObjectApiResponse();
		if (StringUtils.hasText(dto.getStatus()) || StringUtils.hasText(dto.getMessage())) {
			dto.setStatus(MethodMessage.MSG_GET.getStatus().getEn());
			dto.setMessage(MethodMessage.MSG_GET.getEn());
		}
		dto.setData(data);
		
		return dto;
	}
	
	public <T> ObjectPagedApiResponse responseApiPaged(Page<T> page) {

		ObjectPagedApiResponse dto = new ObjectPagedApiResponse();
		if (StringUtils.hasText(dto.getStatus()) || StringUtils.hasText(dto.getMessage())) {
			dto.setStatus(MethodMessage.MSG_GET.getStatus().getEn());
			dto.setMessage(MethodMessage.MSG_GET.getEn());
		}
		dto.setData(page.getContent());
		dto.setPage(page.getNumber());
		dto.setTotalElement(page.getTotalElements());
		dto.setTotalPages(page.getTotalPages());
		
		return dto;
	}

}
