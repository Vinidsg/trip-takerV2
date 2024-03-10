package com.senac.tripTaker.controller;

import com.senac.tripTaker.model.Trip;
import com.senac.tripTaker.service.AmazonS3Service;
import com.senac.tripTaker.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TripController {

    private final TripService tripService;
    private final AmazonS3Service amazonS3Service;

    @Autowired
    public TripController(TripService tripService, AmazonS3Service amazonS3Service) {
        this.tripService = tripService;
        this.amazonS3Service = amazonS3Service;
    }

    @GetMapping("/create-trip")
    public String showCreateTripForm() {
        return "createEditTrip";
    }

    @PostMapping("/create-trip")
    public String handleCreateTrip(@RequestParam("image") MultipartFile image, Trip trip) {
        try {
            if (!image.isEmpty()) {
                String imageUrl = amazonS3Service.uploadImage(image);
                trip.setImage(imageUrl);
            }
            tripService.createTrip(trip);
            return "redirect:/getAllTrips"; // Ajuste conforme a rota correta para listar viagens
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @PostMapping("/delete-trip")
    public String deleteTrip(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        tripService.deleteTripById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Viagem deletada com sucesso!");
        return "redirect:/getAllTrips"; // Ajuste conforme a rota correta para listar viagens
    }

    @GetMapping("/find-all-trips")
    public String listAllTrips(Model model) {
        List<Trip> trips = tripService.findAllTrips();
        model.addAttribute("trips", trips);
        return "RotasViagensDatas";  // Nome do arquivo HTML na pasta resources/templates
    }
    @GetMapping("/gerenciar-trips")
    public String gerenciarTrips(Model model, @SessionAttribute(name = "username", required = false) String username) {
        if (username == null) {
            // Usuário não está logado, redirecionar para a página de login
            return "redirect:/login";
        }

        // Usuário está logado, mostrar página de gerenciamento
        List<Trip> trips = tripService.findAllTrips();
        model.addAttribute("trips", trips);
        return "gerenciarRotas";  // Nome do arquivo HTML na pasta resources/templates
    }

}
