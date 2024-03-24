package com.senac.tripTaker.controller;

import com.senac.tripTaker.model.Trip;
import com.senac.tripTaker.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/trips")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        try {
            Trip createdTrip = tripService.createTrip(trip);
            return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        try {
            tripService.deleteTripById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/trip_list")
    public ResponseEntity<List<Trip>> listAllTrips() {
        try {
            List<Trip> trips = tripService.findAllTrips();
            return new ResponseEntity<>(trips, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
