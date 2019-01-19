package com.urlshortener.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class UrlClick {
	
	@Id
	@Column(name = "URL_CLICK_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column(name="DATE_CLICK")
	@NonNull private LocalDateTime date;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@NonNull private UrlModel urlModel;
	
	@Column(name="IP")
	@NonNull private String ip;
	

}
