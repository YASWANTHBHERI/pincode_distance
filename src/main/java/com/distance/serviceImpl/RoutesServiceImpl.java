package com.distance.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distance.dto.ApiRequestBody;
import com.distance.dto.RoutesResponse;
import com.distance.entities.Destination;
import com.distance.entities.Origin;
import com.distance.entities.Route;
import com.distance.entities.embedable.Routes;
import com.distance.repository.DestinationRepository;
import com.distance.repository.OriginRepository;
import com.distance.repository.RouteRepository;
import com.distance.service.LatitudeAndLongitude;
import com.distance.service.RoutesService;

@Service
public class RoutesServiceImpl implements RoutesService {

	@Autowired
	private LatitudeAndLongitude latitudeAndLongitude;

	@Autowired
	private OriginRepository originRepo;

	@Autowired
	private DestinationRepository destinationRepo;
	
	@Autowired
	private RouteServiceExternalAPI routeServiceExternalAPI;
	
	@Autowired
	private RouteRepository routeRepo;
	
	private Logger logger = LoggerFactory.getLogger(RoutesServiceImpl.class);

//	get list of origin latitude and longitude cordinates
	public List<Double> getOriginLatAndLong(String originPincode) {
		Optional<Origin> originCordinates = originRepo.findByPincode(originPincode);
		List<Double> originCordinatesList;

		// checking whether fromPincode latitude and longitude are present in repository
		if (!originCordinates.isPresent()) {
			logger.info("Making an API Call to get Origin Latitude and Longitude :{}",originPincode);
			originCordinatesList = latitudeAndLongitude.getLatAndLong(originPincode);
			Origin origin = new Origin();
			origin.setPincode(originPincode);
			origin.setLatitude(originCordinatesList.get(0));
			origin.setLongitude(originCordinatesList.get(1));
			originRepo.save(origin);
			return originCordinatesList;
		}
		originCordinatesList = new ArrayList<>();
		originCordinatesList.add(originCordinates.get().getLatitude());
		originCordinatesList.add(originCordinates.get().getLongitude());
		return originCordinatesList;

	}
	
	// get list of destination latitude and longitude cordintates	
	public List<Double> getDestinationLatAndLong(String destinationPincode){
		Optional<Destination> destinationCordinates = destinationRepo.findByPincode(destinationPincode);
		List<Double>destinationCordinatesList;
		if(!destinationCordinates.isPresent()) {
			logger.info("Making an API Call to get Destination Latitude and Longitude :{}",destinationPincode);
			destinationCordinatesList = latitudeAndLongitude.getLatAndLong(destinationPincode);
			
			Destination destination = new Destination();
			destination.setPincode(destinationPincode);
			destination.setLatitude(destinationCordinatesList.get(0));
			destination.setLongitude(destinationCordinatesList.get(1));
			
			destinationRepo.save(destination);
			return destinationCordinatesList;
			
		}
		
		destinationCordinatesList = new ArrayList<>();
		destinationCordinatesList.add(destinationCordinates.get().getLatitude());
		destinationCordinatesList.add(destinationCordinates.get().getLongitude());
		return destinationCordinatesList;
		
		
	}

	@Override
	public RoutesResponse  getRoutes(String originPincode, String destinationPincode) {
		
		logger.info("get Routes Method called..");
		
		List<Double> originCordinates = this.getOriginLatAndLong(originPincode);
		List<Double> destinationCordinates = this.getDestinationLatAndLong(destinationPincode);
		
		Double originLatitude = originCordinates.get(0);
		Double originLongitude = originCordinates.get(1);
		
		Double destinationLatitude = destinationCordinates.get(0);
		Double destinationLongitude = destinationCordinates.get(1);
		
		logger.info("Cordinates {} {} {} {} :", originLatitude, originLongitude, destinationLatitude, destinationLongitude);
		
		ApiRequestBody apiRequestBody = new ApiRequestBody();
		
		JSONObject requestBody = apiRequestBody.getRouteApiJsonRequestBody(originLatitude,originLongitude,destinationLatitude,destinationLongitude);
		
		logger.info("Request Body to Make API Call",requestBody.toString());
		
		Optional<Route>route = routeRepo.findByOriginAndDestination(
				originRepo.findByPincode(originPincode).get(), destinationRepo.findByPincode(destinationPincode).get()
				);
		if(!route.isPresent()) {
			logger.info("Making an API Call to get Routes of Origin :{} Destination :{}: ",originPincode,destinationPincode);
			List<Routes> response = routeServiceExternalAPI.getRoutesResponse(requestBody);
			
			Route newRoute = new Route();
			newRoute.setOrigin(originRepo.findByPincode(originPincode).get());
			newRoute.setDestination(destinationRepo.findByPincode(destinationPincode).get());
			newRoute.setRoutesList(response);			
			routeRepo.save(newRoute);
			return new RoutesResponse(response);
		}
		
//		List<Routes> response = routeServiceExternalAPI.getRoutesResponse(requestBody);
		
//		logger.info("getting routes response in routes service impl.. {}",response);
		
		return new RoutesResponse(route.get().getRoutesList());
	}

	

}
