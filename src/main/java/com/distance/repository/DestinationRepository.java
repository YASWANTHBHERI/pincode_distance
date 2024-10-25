package com.distance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distance.entities.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

	Optional<Destination> findByPincode(String to_pincode);

}
