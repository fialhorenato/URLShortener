package com.urlshortener.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortener.model.UrlClick;
import com.urlshortener.model.UrlModel;
import com.urlshortener.repository.UrlClickRepository;
import com.urlshortener.repository.UrlRepository;

@Service
public class UrlClickService {
	
	@Autowired
	private UrlClickRepository urlClickRepository;
	
	@Autowired
	private UrlRepository urlRepository;
	
	public Integer countClicksById(String id) {
		Optional<UrlModel> model = urlRepository.findById(id);
		if (model.isPresent()) {
			return urlClickRepository.countClicksByUrl(model.get());
		} else {
			return -1;
		}
	}
	
	public List<UrlClick> getAllClicksById(String id) {
		Optional<UrlModel> model = urlRepository.findById(id);
		if (model.isPresent()) {
			return urlClickRepository.getAllClicksByUrl(model.get());
		} else {
			return new ArrayList<>();
		}
	}
}
