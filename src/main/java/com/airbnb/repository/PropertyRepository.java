package com.airbnb.repository;

import com.airbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT p FROM Property p JOIN p.location l JOIN p.country c WHERE l.locationName = :locationName OR c.countryName = :locationName")
    List<Property> findPropertyByLocation(@Param("locationName") String location);





}