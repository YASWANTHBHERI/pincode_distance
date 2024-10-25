package com.distance.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.distance.serviceImpl.RoutesServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class PincodeDistanceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private RoutesServiceImpl routesServiceImpl;
	
	@InjectMocks
	private PincodeDistanceController pincodeDistanceController;
	
	@Test
	public void testEndPoint_ReturnsStatus_OK() throws Exception {
		String originPincode = "141106";
		String destinationPincode = "110060";
		
		mockMvc.perform(get("/api/routes/" + originPincode + "-" + destinationPincode)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	
	@Test
	public void testEndPoint_ReturnsBadRequest() throws Exception{
		String originPincode = "53200"; //inserting a invalid length
		String destinationPincode = "530001";
		
		String url = "/api/routes/"+originPincode+"-"+destinationPincode;
		mockMvc.perform(get(url)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		
	}


}
