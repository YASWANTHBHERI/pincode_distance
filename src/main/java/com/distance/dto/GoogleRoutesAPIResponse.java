package com.distance.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoogleRoutesAPIResponse {
	
	@JsonProperty("routes")
	private List<ApiResponseBody>routesList;

}
