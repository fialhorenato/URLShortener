package com.urlshortener.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class UrlModel {
	
	@Id
	@NonNull private String id;
	
	@Column(name="URL")
	@NonNull private String url;
	
	@OneToMany(mappedBy="urlModel", cascade=CascadeType.ALL)
	private List<UrlClick> clicks;
}
