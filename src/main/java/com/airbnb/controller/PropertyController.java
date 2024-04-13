package com.airbnb.controller;

import com.airbnb.entity.Property;
import com.airbnb.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/property")
public class PropertyController {

   @Autowired
    private PropertyRepository propertyRepository;




    @GetMapping("{locationName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Property> findLocationById(@PathVariable String locationName){


           List<Property> propertyByLocation= propertyRepository.findPropertyByLocation(locationName);

        return propertyByLocation;
    }
}
