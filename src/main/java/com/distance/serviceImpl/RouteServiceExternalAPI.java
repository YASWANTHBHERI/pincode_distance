package com.distance.serviceImpl;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.distance.config.AppConfig;
import com.distance.dto.ApiResponseBody;
import com.distance.dto.GoogleRoutesAPIResponse;
import com.distance.entities.embedable.Routes;

@Service
public class RouteServiceExternalAPI {

	@Autowired
	private AppConfig appConfig;

	private final Logger logger = LoggerFactory.getLogger(RouteServiceExternalAPI.class);

	public List<Routes> getRoutesResponse(JSONObject requestBody) {

		String url = "https://routes.googleapis.com/directions/v2:computeRoutes";

		final String Google_Maps_ApiKey = appConfig.getGoogleAPIKey();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("X-Goog-Api-Key", Google_Maps_ApiKey);
		httpHeaders.set("X-Goog-FieldMask",
				"routes.distanceMeters,routes.duration,routes.polyline.encodedPolyline,routes.routeLabels");

		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody.toString(), httpHeaders);
		
		logger.info("requestBody to string :{}",requestBody.toString());

		RestTemplate restTemplate = new RestTemplate();

		GoogleRoutesAPIResponse routesResponse = restTemplate
				.exchange(url, HttpMethod.POST, httpEntity, GoogleRoutesAPIResponse.class).getBody();
		
//		ResponseEntity<String> routesResponseStr = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
//		logger.info("Getting response from routes: {}",routesResponseStr.getBody());
		
		logger.info("routes response: {}", routesResponse);

		return getRoutesDetailsFromResponse(routesResponse);

	}

	private List<Routes> getRoutesDetailsFromResponse(GoogleRoutesAPIResponse response) {
		List<ApiResponseBody> routesResponse = response.getRoutesList();
		List<Routes> routes = new ArrayList<>();

		for (int i = 0; i < routesResponse.size(); i++) {
			routes.add(new Routes(routesResponse.get(i).getDistanceMeters(), routesResponse.get(i).getDuration(),
					routesResponse.get(i).getPolyline().getEncodedPolyline(), routesResponse.get(i).getRouteLabels()));
		}
		return routes;
	}

}
