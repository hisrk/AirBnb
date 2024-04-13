package com.airbnb.repository;

import com.airbnb.entity.Property;
import com.airbnb.entity.PropertyUser;
import com.airbnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByPropertyUserIdAndPropertyId(long propertyUserId, long propertyId);

    //we can also do it this way--->

      //@Query("select r from Review r where r.property=:property and r.propertyUser=:user")
     // Review findReviewByUser(@Param("property") Property property, @Param("user")PropertyUser propertyUser);


    List<Review> findReviewByPropertyUserId(Long propertyUserId);



}