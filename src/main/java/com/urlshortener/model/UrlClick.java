package com.urlshortener.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@RedisHash("UrlClick")
public class UrlClick {
	
	
	@JsonIgnore @Id Long id;
	
	@NonNull private LocalDateTime date;
	
	@JsonIgnore @Indexed @Reference @NonNull private UrlModel urlModel;
	
	@NonNull private String ip;
	

}
