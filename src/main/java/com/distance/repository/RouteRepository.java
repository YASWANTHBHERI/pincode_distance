package com.distance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distance.entities.Destination;
import com.distance.entities.Origin;
import com.distance.entities.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
	Optional<Route>findByOriginAndDestination(Origin origin, Destination destination);
}
