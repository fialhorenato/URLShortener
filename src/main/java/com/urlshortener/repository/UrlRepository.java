package com.urlshortener.repository;

import org.springframework.data.repository.CrudRepository;

import com.urlshortener.model.UrlModel;


public interface UrlRepository extends CrudRepository<UrlModel, String> {}
