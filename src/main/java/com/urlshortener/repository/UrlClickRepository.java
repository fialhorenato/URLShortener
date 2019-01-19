package com.urlshortener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.urlshortener.model.UrlClick;
import com.urlshortener.model.UrlModel;


public interface UrlClickRepository extends CrudRepository<UrlClick, String> {
	
	@Query("Select count(1) from UrlClick uc where uc.urlModel = :urlModel")
	Integer	countClicksByUrl(UrlModel urlModel);
	
	@Query("Select uc from UrlClick uc where uc.urlModel = :urlModel")
	List<UrlClick> getAllClicksByUrl(UrlModel urlModel);

}
