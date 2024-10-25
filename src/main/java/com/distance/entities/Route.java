package com.distance.entities;

import java.util.List;

import com.distance.entities.embedable.Routes;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Route {
	

	@Id
	@GeneratedValue
	private Long routeId;
	
	@Embedded
	@ElementCollection
	@CollectionTable(name="routes_list",joinColumns = @JoinColumn(name="route_id"))
	private List<Routes> routesList;
	
	@ManyToOne
	@JoinColumn(name="origin_id")
	private Origin origin;
	
	@ManyToOne
	@JoinColumn(name="destination_id")
	private Destination destination;
	

	public Route(List<Routes> routesList, Origin origin, Destination destination) {
		this.routesList = routesList;
		this.origin = origin;
		this.destination = destination;
	}
	
	
	
	
	

}
