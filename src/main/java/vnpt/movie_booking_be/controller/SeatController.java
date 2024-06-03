package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.response.SeatResponse;
import vnpt.movie_booking_be.service.SeatService;

import java.util.List;

@RestController
@RequestMapping("/seat")
@CrossOrigin("*")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/getSeatsById/{cinemaId}")
    public List<SeatResponse> getSeatsById(@PathVariable int cinemaId) {
        return seatService.getSeatsById(cinemaId);
    }
}
