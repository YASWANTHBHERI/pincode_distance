package com.distance.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Destination {
	@Id
	@GeneratedValue
	private Long destinationId;
	
	private String pincode;
	
	private double latitude;
	private double longitude;
	
	@OneToMany(mappedBy = "destination")
	private Set<Route> routes;
	

}
