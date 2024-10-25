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
public class Origin {

	@Id
	@GeneratedValue
	private Long originId;

	private String pincode;

	private double latitude;
	private double longitude;

	@OneToMany(mappedBy = "origin")
	private Set<Route> routes;

}
