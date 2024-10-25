package com.distance.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.distance.entities.embedable.Routes;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RouteServiceExternalAPITest {

	
	@Autowired
	private RouteServiceExternalAPI routeServiceExternalAPI;
	
	
	JSONObject requestBody = new JSONObject();
	
	@BeforeAll
	public void init() {
		Double originLatitude = 18.2890868;
		Double originLongitude = 83.9055997;
		
		Double destinationLatitude = 17.4103863;
		Double destinationLongitude = 78.5002018;
		
		requestBody.put("origin", new JSONObject().put("location", new JSONObject().put("latLng",
				new JSONObject().put("latitude", originLatitude).put("longitude", originLongitude))));

		requestBody.put("destination", new JSONObject().put("location", new JSONObject().put("latLng",
				new JSONObject().put("latitude", destinationLatitude).put("longitude", destinationLongitude))));

		requestBody.put("travelMode", "DRIVE");
		requestBody.put("computeAlternativeRoutes", true);
		requestBody.put("languageCode", "en-IN");
		requestBody.put("units", "METRIC");

	}
	
	@Test
	public void getRoutesResponseFromAPI() {
		List<Routes> routesResponse = routeServiceExternalAPI.getRoutesResponse(requestBody);
		
		assertNotNull(routesResponse);
		assertFalse(routesResponse.isEmpty());
		assertNotNull(routesResponse.get(0));
	}
	
}
