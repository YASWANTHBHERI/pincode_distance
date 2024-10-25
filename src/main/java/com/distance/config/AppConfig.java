package com.distance.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Value("${api.googleApiKey}")
	private String googleApiKey;
	
	public String getGoogleAPIKey() {
		return googleApiKey;
	}

}
