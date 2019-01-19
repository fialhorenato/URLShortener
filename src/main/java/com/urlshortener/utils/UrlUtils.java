package com.urlshortener.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import com.google.common.hash.Hashing;
import com.urlshortener.model.UrlDTO;
import com.urlshortener.model.UrlModel;

public class UrlUtils {
	
	private UrlUtils() {}

	public static boolean isUrlValid(String url) {
		try {
			new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}

	public static Optional<UrlModel> createUrlModelFromUrlDTO(UrlDTO url) {
		if (Optional.ofNullable(url.getUrl()).isPresent() && isUrlValid(url.getUrl())) {
			UrlModel urlModel = new UrlModel(createHashFromUrl(url.getUrl()), url.getUrl());
			return Optional.of(urlModel);
		}
		return Optional.empty();
	}

	public static String createHashFromUrl(String url) {
		return Hashing.murmur3_128().hashString(url, StandardCharsets.UTF_8).toString();
	}

	public static URI getURIFromUrl(String url) {
		try {
			return new URI(url);
		} catch (URISyntaxException e) {
			return null;
		}
	}

}
