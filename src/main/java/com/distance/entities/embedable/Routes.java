package com.distance.entities.embedable;


import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Routes {
	
	@Nonnull
	private Long distanceMeters;
	
	@Nonnull
	private String duration;
	
	@Nonnull
	@Column(columnDefinition = "TEXT")
	private String encodedPolyline;
	
	@Nonnull
	private List<String> routeLabels;

}

