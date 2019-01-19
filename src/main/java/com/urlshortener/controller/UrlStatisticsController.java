package com.urlshortener.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.model.UrlClick;
import com.urlshortener.service.UrlClickService;

@RestController
@RequestMapping(value="/statistics")
public class UrlStatisticsController {
	
	@Autowired
	private UrlClickService urlClickService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Map<String,Integer>> getCountClicksById(@PathVariable String id) {
		Integer clicks = urlClickService.countClicksById(id);
		Map<String, Integer> map = new HashMap<>();
		map.put("clicks", clicks);
		return clicks.compareTo(-1) != 0 ? new ResponseEntity<>(map,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/all/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<UrlClick>> getAllClicksById(@PathVariable String id) {
		return new ResponseEntity<>(urlClickService.getAllClicksById(id),HttpStatus.OK);
	}
	
}
