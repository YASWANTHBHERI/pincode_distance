package com.distance.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.distance.dto.RoutesResponse;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoutesServiceImplTest {

	@Autowired
	private RoutesServiceImpl routesServiceImpl;
	
	String originPincode;
	String destinationPincode;
	
	@BeforeAll
	public void init() {
		originPincode = "532001";
		destinationPincode = "530001";
	}
	
	
	@Test
	public void getOriginCordinates() {
		List<Double> originCordinates = routesServiceImpl.getOriginLatAndLong(originPincode);
		assertNotNull(originCordinates.get(0));
		assertNotNull(originCordinates.get(1));
	}
	
	@Test
	public void getDestinationCordinates() {
		List<Double> destinationCordinates = routesServiceImpl.getDestinationLatAndLong(destinationPincode);
		assertNotNull(destinationCordinates.get(0));
		assertNotNull(destinationCordinates.get(1));
	}
	
	@Test
	public void getAllRoutes() {
		RoutesResponse routesResponse = routesServiceImpl.getRoutes(originPincode, destinationPincode);
		assertNotNull(routesResponse);		
	}
	
}
