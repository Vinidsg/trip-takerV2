package com.senac.tripTaker.service;

import com.senac.tripTaker.model.Trip;
import com.senac.tripTaker.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<Trip> findAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> findTripById(Long id) {
        return tripRepository.findById(id);
    }

    public void deleteTripById(Long id) {
        tripRepository.deleteById(id);
    }

    public Trip updateTrip(Trip trip) {
        return tripRepository.save(trip);
    }
}
