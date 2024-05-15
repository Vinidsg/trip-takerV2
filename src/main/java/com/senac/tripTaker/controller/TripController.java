package com.senac.tripTaker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.senac.tripTaker.model.Trip;
import com.senac.tripTaker.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class TripController {

    private final ObjectMapper mapper = new ObjectMapper();

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PatchMapping(path = "/trips/edit/{id}")
    public ResponseEntity<Trip> editTrip(@PathVariable Long id, @RequestParam("data") String data,
                                         @RequestParam(value = "files", required = false) MultipartFile files) {

        try {
            Optional<Trip> trip = tripService.findTripById(id);
            JsonPatch patch = JsonPatch.fromJson(mapper.readTree(data));
            Trip tripPatched = applyPatchToCustomer(patch, trip.orElse(null));
            if (files != null && !files.isEmpty()) {
                String imagePath = tripService.saveImage(files);
                tripPatched.setImage(imagePath);
            }
            System.out.println(tripPatched);
            tripService.updateTrip(tripPatched);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Trip applyPatchToCustomer(JsonPatch patch, Trip targetTrip) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(mapper.convertValue(targetTrip, JsonNode.class));
        return mapper.treeToValue(patched, Trip.class);
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

    @GetMapping("/trip_id/{id}")
    public ResponseEntity findTripById(@PathVariable Long id){
        Optional<Trip> trip = tripService.findTripById(id);

        if (trip.isPresent()){
            return ResponseEntity.ok(trip);
        } else {
            return ResponseEntity.badRequest().body("Trip não encontrada");
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

    @PostMapping("/create")
    public ResponseEntity<Trip> createTrip(@RequestParam String data,
                                           @RequestParam(value = "files", required = false) MultipartFile files)  {

        try {
           var trip = mapper.readValue(data, Trip.class);
            String filepath = tripService.saveImage(files);
            trip.setImage(filepath);
            Trip createdTrip = tripService.createTrip(trip);
            return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
