package com.urlshortener.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.hash.Hashing;
import com.urlshortener.model.UrlClick;
import com.urlshortener.model.UrlDTO;
import com.urlshortener.model.UrlModel;
import com.urlshortener.repository.UrlRepository;

@Service
public class UrlService {

	@Autowired
	private UrlRepository urlRepository;

	public UrlModel save(UrlModel urlModel) {
		return this.urlRepository.save(urlModel);
	}

	public Optional<UrlModel> getUrlByID(String id) {
		return this.urlRepository.findById(id);
	}

	public void insertClickIntoUrlModel(Optional<UrlModel> urlModel) {
		if (urlModel.isPresent()) {
			urlModel.get().setClicks(new ArrayList<>());
			Date input = new Date();
			LocalDateTime date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
			String ip = request.getRemoteAddr();
			urlModel.get().getClicks().add(new UrlClick(date, urlModel.get(), ip));
			this.urlRepository.save(urlModel.get());
		}
	}

	public ResponseEntity<UrlModel> mountResponseEntityToRedirect(Optional<UrlModel> urlModel) {
		if (urlModel.isPresent()) {
			if (isValid(urlModel.get().getUrl())) {
				HttpHeaders headers = new HttpHeaders();
				try {
					headers.setLocation(new URI(urlModel.get().getUrl()));
				} catch (URISyntaxException e) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(headers, HttpStatus.PERMANENT_REDIRECT);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public Optional<UrlModel> createUrlModelFromUrlDTO(UrlDTO url) {
		if (Optional.ofNullable(url.getUrl()).isPresent() && isValid(url.getUrl())) {
			UrlModel urlModel = new UrlModel(createHashFromUrl(url.getUrl()), url.getUrl());
			return Optional.of(urlModel);
		}
		return Optional.empty();
	}
	
	private String createHashFromUrl(String url) {
		return Hashing.murmur3_128().hashString(url, StandardCharsets.UTF_8).toString();
	}

	private boolean isValid(String url) {
		try {
			new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}
}
