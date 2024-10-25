package com.distance.dto;

import java.util.List;

import com.distance.entities.embedable.Routes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoutesResponse {
	
	private List<Routes>routes;
}
