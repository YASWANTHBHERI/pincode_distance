package com.distance.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.distance.config.AppConfig;
import com.distance.helper.JsonFormatterHelper;
import com.distance.service.LatitudeAndLongitude;

@Service
public class LatAndLongImpl implements LatitudeAndLongitude{
	
	@Autowired
	private AppConfig appConfig;
	
	
	 
	private final RestTemplate restTemplate = new RestTemplate();
	
	private Logger logger = LoggerFactory.getLogger(LatAndLongImpl.class);
	
	private List<Double> getLatAndLongFromGeocode(String pincode) {
		
		final String Google_Maps_ApiKey = appConfig.getGoogleAPIKey();
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + pincode + "&key=" + Google_Maps_ApiKey;
		String response = restTemplate.getForObject(url, String.class);
		
		if(response.isEmpty()) {
			logger.warn("getting empty response from Latitude and Longitude ApiCall for Pincode :{} ",pincode);
			return null;
		}
		
		logger.info("getting latitude and longitude for pincode: {}  Response: {}",pincode,response);
		
//		Helper class to Format and get value of Response
		JsonFormatterHelper jsonFormatterHelper = new JsonFormatterHelper();
		List<Double>latAndLongList = jsonFormatterHelper.converJsonToListOfLatAndLong(response);
		return latAndLongList;
		
	}

	@Override
	public List<Double> getLatAndLong(String pincode) {
		return getLatAndLongFromGeocode(pincode);
	}
	
	

	

}
