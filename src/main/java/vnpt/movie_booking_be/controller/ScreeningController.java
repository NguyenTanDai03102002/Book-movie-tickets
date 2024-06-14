package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.request.ScreeningCreationRequest;
import vnpt.movie_booking_be.dto.response.ScreeningResponse;
import vnpt.movie_booking_be.service.ScreeningService;

import java.util.List;

@RestController
@RequestMapping("/screening")
@CrossOrigin("*")
public class ScreeningController {

    @Autowired
    private ScreeningService screeningService;

    @GetMapping("/getScreeningByCinema")
    public List<ScreeningResponse> getScreeningByCinema(@RequestParam int cinemaId) {
        return screeningService.getScreeningByCinema(cinemaId);
    }

    @GetMapping("/getScreeningByCityAndMovie")
    public List<ScreeningResponse> getScreeningByCityAndMovie(@RequestParam String city,@RequestParam int movieId){
        return screeningService.getScreeningByCityAndMovie(city,movieId);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/createScreening")
    public void createScreening(@RequestBody ScreeningCreationRequest request){
        screeningService.createScreening(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/updateScreening/{screeningId}")
    public void updateScreening(@PathVariable int screeningId ,@RequestBody ScreeningCreationRequest request){
        screeningService.updateScreening(screeningId,request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/deleteScreening/{screeningId}")
    public void deleteScreening(@PathVariable int screeningId ){
        screeningService.deleteScreening(screeningId);
    }
}
