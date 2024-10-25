package com.distance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distance.entities.Origin;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {
	
	Optional<Origin> findByPincode(String pincode);

}
