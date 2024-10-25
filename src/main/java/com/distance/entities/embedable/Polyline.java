package com.distance.entities.embedable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Polyline {
	
	@Column(columnDefinition = "TEXT")
	@JsonProperty("encodedPolyline")
	private String encodedPolyline;

}
