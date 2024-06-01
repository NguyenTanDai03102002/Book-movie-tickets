package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.response.ScreeningResponse;
import vnpt.movie_booking_be.service.ScreeningService;

import java.util.List;

@RestController
@RequestMapping("/screening")
@CrossOrigin("*")
public class ScreeningController {

    @Autowired
    private ScreeningService screeningService;

    @GetMapping("/getScreeningByCityAndMovie")
    public List<ScreeningResponse> getScreeningByCityAndMovie(@RequestParam String city,@RequestParam int movieId){
        return screeningService.getScreeningByCityAndMovie(city,movieId);
    }

}
