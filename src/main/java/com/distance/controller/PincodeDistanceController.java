package com.distance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distance.service.RoutesService;

@RestController
@RequestMapping("/api")
public class PincodeDistanceController {

	@Autowired
	private RoutesService routesService;

	@GetMapping("/routes/{from_pincode}-{to_pincode}")

	public ResponseEntity<Object> getRoutes(@PathVariable String from_pincode, @PathVariable String to_pincode) {

		if (from_pincode.length() != 6 || to_pincode.length() != 6)
			return new ResponseEntity<>("Invalid Pincode", HttpStatus.BAD_REQUEST);

		try {
			return new ResponseEntity<>(routesService.getRoutes(from_pincode, to_pincode), HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>("Resource Not Found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
