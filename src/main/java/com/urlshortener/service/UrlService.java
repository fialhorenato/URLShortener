package com.urlshortener.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.urlshortener.model.UrlClick;
import com.urlshortener.model.UrlModel;
import com.urlshortener.repository.UrlRepository;

@Service
public class UrlService {

	@Autowired
	private UrlRepository urlRepository;
	
	@Autowired 
	private UrlClickService urlClickService;

	public UrlModel save(UrlModel urlModel) {
		return this.urlRepository.save(urlModel);
	}

	public Optional<UrlModel> findById(String id) {
		Optional<UrlModel> urlModel = this.urlRepository.findById(id);
		if (urlModel.isPresent()) {
			insertClickIntoUrlModel(urlModel.get());
		}
		return this.urlRepository.findById(id);
	}

	private void insertClickIntoUrlModel(UrlModel urlModel) {
			this.urlClickService.save(new UrlClick(getToday(), urlModel, getIp()));
	}

	private LocalDateTime getToday() {
		return new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	private String getIp() {
		return((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest().getRemoteAddr();
	}
	
	
}
