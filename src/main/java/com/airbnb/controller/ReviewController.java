package com.airbnb.controller;


import com.airbnb.entity.Property;
import com.airbnb.entity.PropertyUser;
import com.airbnb.entity.Review;
import com.airbnb.payload.ReviewDto;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.PropertyUserRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {



@Autowired
private ReviewRepository reviewRepository;


@Autowired
private PropertyRepository propertyRepository;


@Autowired
private PropertyUserRepository propertyUserRepository;



    @PostMapping("/saveReview")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> postReview(@RequestBody Review review,
                             @RequestParam long propertyUserId,
                             @RequestParam long propertyId) {

        Optional<Review> reviewByPropertyUserId = reviewRepository.findByPropertyUserIdAndPropertyId(propertyUserId, propertyId);
//reply to the comment only and once comment for obne propertyy
        if (reviewByPropertyUserId.isPresent()) {
            return ResponseEntity.ok("review already exists by this id");
        } else {
            Optional<Property> propertyOptional = propertyRepository.findById(propertyId);

            Optional<PropertyUser> propertyUserOptional = propertyUserRepository.findById(propertyUserId);

            if (propertyOptional.isPresent() && propertyUserOptional.isPresent()) {
                Property property = propertyOptional.get();
                PropertyUser propertyUser = propertyUserOptional.get();

                review.setProperty(property);
                review.setPropertyUser(propertyUser);

                Review save = reviewRepository.save(review);
                return ResponseEntity.ok(save);
            } else {

                return null; // or throw an exception
            }
        }
    }

    @GetMapping("/propertyUser/{propertyUserId}") // Corrected path pattern
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Review>> getAllReviewByPropertyUser(@PathVariable Long propertyUserId) {
        List<Review> reviewByPropertyUser = reviewRepository.findReviewByPropertyUserId(propertyUserId);

        // Check if any reviews were found
        if (!reviewByPropertyUser.isEmpty()) {
            return new ResponseEntity<>(reviewByPropertyUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if no reviews found
        }
    }







}
