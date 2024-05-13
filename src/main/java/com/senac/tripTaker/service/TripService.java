package com.senac.tripTaker.service;

import com.senac.tripTaker.model.Trip;
import com.senac.tripTaker.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    public String saveImage(MultipartFile file) throws IOException {
        Long currentTime = new Date().getTime();
        String fileName = currentTime.toString().concat("-").concat(Objects.requireNonNull(file.getOriginalFilename()).replace(" ", ""));
        Files.copy(file.getInputStream(), Path.of("src/main/resources/static/imgPath/" + fileName),
                StandardCopyOption.REPLACE_EXISTING);
        return "imgPath/" + fileName;
    }
}
