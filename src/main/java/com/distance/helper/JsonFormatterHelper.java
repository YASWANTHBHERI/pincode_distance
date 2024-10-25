package com.distance.helper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonFormatterHelper {

	private Logger logger = LoggerFactory.getLogger(JsonFormatterHelper.class);

	public List<Double> converJsonToListOfLatAndLong(String response) {
		logger.info("JsonFormatter Helpe: response: {}", response);

		try {
			JSONObject jsonObjectResponse = new JSONObject(response).getJSONArray("results").getJSONObject(0)
					.getJSONObject("geometry").getJSONObject("location");

			List<Double> latAndLongCordinates = new ArrayList<>();
			latAndLongCordinates.add(jsonObjectResponse.getDouble("lat"));
			latAndLongCordinates.add(jsonObjectResponse.getDouble("lng"));

			return latAndLongCordinates;

		} catch (Exception e) {
			return null;
		}

	}

}
