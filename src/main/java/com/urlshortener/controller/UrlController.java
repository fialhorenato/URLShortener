package com.urlshortener.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.model.UrlDTO;
import com.urlshortener.model.UrlModel;
import com.urlshortener.service.UrlService;

@RestController
public class UrlController {
	
	@Autowired
	private UrlService urlService;
	
	@GetMapping(value="/{id}", produces = MediaType.ALL_VALUE)
	public ResponseEntity<UrlModel> redirectToSavedUrl(@PathVariable String id) {
		Optional<UrlModel> urlModel = urlService.getUrlByID(id);
		urlService.insertClickIntoUrlModel(urlModel);
		return urlService.mountResponseEntityToRedirect(urlModel);
	}
	
	@PostMapping(value="/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UrlModel> saveURL(@RequestBody UrlDTO urlDTO) {
		Optional<UrlModel> urlModel = urlService.createUrlModelFromUrlDTO(urlDTO);
		if (urlModel.isPresent()) {
			return new ResponseEntity<>(urlService.save(urlModel.get()),HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	

}

