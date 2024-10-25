package com.distance.dto;

import java.util.List;

import com.distance.entities.embedable.Polyline;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseBody {
	
	@JsonProperty("distanceMeters")
	private Long distanceMeters;
	
	@JsonProperty("duration")
	private String duration;
	
	@JsonProperty("polyline")
	private Polyline polyline;
	
	@JsonProperty("routeLabels")
	private List<String>routeLabels;
}
