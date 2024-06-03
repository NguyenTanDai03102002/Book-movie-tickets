package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.response.FactorResponse;
import vnpt.movie_booking_be.service.FactorService;

import java.util.List;

@RestController
@RequestMapping("/factor")
@CrossOrigin("*")
public class FactorController {

    @Autowired
    private FactorService factorService;

    @GetMapping("/getAllFactorByCinema/{cinemaId}")
    public List<FactorResponse> getAllFactorByCinema(@PathVariable("cinemaId") int cinemaId) {
        return factorService.getAllFactorByCinema(cinemaId);
    }
}
