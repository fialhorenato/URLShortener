package com.urlshortener.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
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
import com.urlshortener.utils.UrlUtils;

@RestController
public class UrlController {
	
	@Autowired
	private UrlService urlService;

	@GetMapping(value = "/{id}", produces = MediaType.ALL_VALUE)
	public ResponseEntity<UrlModel> redirectToSavedUrl(@PathVariable String id) {
		Optional<UrlModel> urlModel = urlService.findById(id);
		return mountResponseEntityToRedirect(urlModel);
	}

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UrlModel> saveURL(@RequestBody UrlDTO urlDTO) {
		Optional<UrlModel> urlModel = UrlUtils.createUrlModelFromUrlDTO(urlDTO);
		if (urlModel.isPresent()) {
			return new ResponseEntity<>(urlService.save(urlModel.get()), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<UrlModel> mountResponseEntityToRedirect(Optional<UrlModel> urlModel) {
		if (urlModel.isPresent() && UrlUtils.isUrlValid(urlModel.get().getUrl())) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(UrlUtils.getURIFromUrl(urlModel.get().getUrl()));
			// NEED NO CACHE CONTROL FOR THE STATISTICS, IT CANNOT CACHE THE REQUEST TO COUNT THE HITS
			headers.setCacheControl(CacheControl.noCache());
			return new ResponseEntity<>(headers, HttpStatus.PERMANENT_REDIRECT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
