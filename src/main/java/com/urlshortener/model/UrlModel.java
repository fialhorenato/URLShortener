package com.urlshortener.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@RedisHash("UrlModel")
public class UrlModel {
	
	
	@Id @NonNull private String id;
	
	@NonNull private String url;
	
	@Reference private List<UrlClick> clicks;
}
