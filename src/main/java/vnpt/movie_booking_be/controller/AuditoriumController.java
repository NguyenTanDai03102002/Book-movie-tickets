package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vnpt.movie_booking_be.service.AuditoriumService;

@RestController
@RequestMapping("/auditorium")
@CrossOrigin("*")
public class AuditoriumController {
    @Autowired
    private AuditoriumService auditoriumService;

    
}
