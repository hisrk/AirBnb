package com.airbnb.controller;

import com.airbnb.entity.Favourite;
import com.airbnb.entity.PropertyUser;
import com.airbnb.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {

//   IMPORTANT CONCEPT ---> Nested object


    //    "id": '1'
    // "property"{

//added on comment
   //---nested object--NESTED JSON
//}
//email feture added

    @Autowired
    private FavouriteRepository  favouriteRepository;


    @PostMapping("/addFavourite")
    public ResponseEntity<Favourite> addFavourite(@RequestBody Favourite favourite,

                                          //Nested OIbject because in favourite we have--
                                           //        1--> PropertyUser user and Propety object

                                          //@Authentication principle---> fecth the authenticated user properyy user
                                           @RequestParam PropertyUser propertyUser

    ){

     favourite.setPropertyUser(propertyUser);





return new ResponseEntity<>(favouriteRepository.save(favourite), HttpStatus.CREATED);

    }










}
