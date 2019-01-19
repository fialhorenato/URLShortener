package com.urlshortener.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.urlshortener.model.UrlClick;
import com.urlshortener.model.UrlModel;


public interface UrlClickRepository extends CrudRepository<UrlClick, String> {
	
	List<UrlClick> findAll();

	List<UrlClick> findAllById(String id);
	
	List<UrlClick> findByUrlModel(UrlModel urlModel);

	
}
