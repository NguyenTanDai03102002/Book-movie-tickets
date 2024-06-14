package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
        import vnpt.movie_booking_be.dto.request.CinemaCreationRequest;
=======
import vnpt.movie_booking_be.dto.request.CinemaCreationRequest;
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
import vnpt.movie_booking_be.dto.response.CinemaResponse;
import vnpt.movie_booking_be.service.CinemaService;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@CrossOrigin("*")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/get/getAll")
    public List<CinemaResponse> getAllCinema() {
        return cinemaService.getAllCinema();
    }

    @GetMapping("/get/getPage")
    public List<CinemaResponse> GetAllCinemaPageable(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size,
                                                     @RequestParam(required = false) String keyword) {
        return cinemaService.GetAllCinemaPageable(PageRequest.of(page,size) , keyword);
    }

    @GetMapping("/get/getCinemasByCity")
    public List<CinemaResponse> getCinemasByCity(@RequestParam String city) {
        return cinemaService.getCinemasByCity(city);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/createCinema")
    public void createCinema(@RequestBody CinemaCreationRequest request) {
        cinemaService.createCinema(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/deleteCinema/{cinemaId}")
    public void deleteCinema(@PathVariable int cinemaId) {
        cinemaService.deleteCinema(cinemaId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/updateCinema/{cinemaId}")
    public void updateCinema(@PathVariable int cinemaId,@RequestBody CinemaCreationRequest request) {
        cinemaService.updateCinema(cinemaId,request);
    }
<<<<<<< HEAD
    @GetMapping("/get/getCinemaById/{cinemaId}")
    public CinemaResponse getCinemaById(@PathVariable int cinemaId) {
        return cinemaService.getCinemaById(cinemaId);
    }

=======
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
}
