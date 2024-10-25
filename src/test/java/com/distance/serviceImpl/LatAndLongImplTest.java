package com.distance.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest 
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LatAndLongImplTest {

	
	@Autowired
	private LatAndLongImpl latAndLongImpl;
	
	
	
	@Test
	public void getLatAndLongCordinates() {
		List<Double> originLatLon = latAndLongImpl.getLatAndLong("532001");
		assertNotNull(originLatLon.get(0));
		assertNotNull(originLatLon.get(1));
		
		List<Double> destinationLatLon = latAndLongImpl.getLatAndLong("530001");
		assertNotNull(destinationLatLon.get(0));
		assertNotNull(destinationLatLon.get(1));
			
	}
    
}
