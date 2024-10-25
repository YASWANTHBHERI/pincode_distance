package com.distance.service;

import com.distance.dto.RoutesResponse;

public interface RoutesService {

	RoutesResponse getRoutes(String originPincode, String destinationPincode);
}
