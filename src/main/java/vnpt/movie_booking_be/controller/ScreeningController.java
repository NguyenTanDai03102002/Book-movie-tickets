package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vnpt.movie_booking_be.service.ScreeningService;

@RestController
@RequestMapping("/movie")
@CrossOrigin("*")
public class ScreeningController {

    @Autowired
    private ScreeningService screeningService;

}
