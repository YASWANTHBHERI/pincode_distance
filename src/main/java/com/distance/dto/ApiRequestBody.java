package com.distance.dto;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApiRequestBody {
	
	private Logger logger = LoggerFactory.getLogger(ApiResponseBody.class);

	public JSONObject getRouteApiJsonRequestBody(Double originLat, Double originLng, Double destinationLat,
			Double destinationLng) {
		
		logger.info("getRouteApiJsonRequestBody called");
		
		JSONObject requestBody = new JSONObject();

		requestBody.put("origin", new JSONObject().put("location", new JSONObject().put("latLng",
				new JSONObject().put("latitude", originLat).put("longitude", originLng))));

		requestBody.put("destination", new JSONObject().put("location", new JSONObject().put("latLng",
				new JSONObject().put("latitude", destinationLat).put("longitude", destinationLng))));

		requestBody.put("travelMode", "DRIVE");
		requestBody.put("computeAlternativeRoutes", true);
		requestBody.put("languageCode", "en-IN");
		requestBody.put("units", "METRIC");

		return requestBody;

	}

}
