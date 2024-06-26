package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.request.AuditoriumCreationRequest;
import vnpt.movie_booking_be.dto.response.AuditoriumResponse;
import vnpt.movie_booking_be.service.AuditoriumService;

import java.util.List;

@RestController
@RequestMapping("/auditorium")
@CrossOrigin("*")
public class AuditoriumController {
    @Autowired
    private AuditoriumService auditoriumService;

    @GetMapping("/get/getAll")
    public List<AuditoriumResponse> getAll(){
        return auditoriumService.getAll();
    }

    @GetMapping("/get/getAuditoriumByCinema/{cinemaId}")
    public List<AuditoriumResponse> getAuditoriumByCinema(@PathVariable int cinemaId){
        return auditoriumService.getAuditoriumByCinema(cinemaId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/createAuditoriumForCinema/{cinemaId}")
    public void createAuditoriumForCinema(@PathVariable int cinemaId, @RequestBody AuditoriumCreationRequest request){
        auditoriumService.createAuditoriumForCinema(cinemaId,request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/deleteAuditoriumForCinema/{auditoriumId}")
    public void deleteAuditoriumForCinema(@PathVariable int auditoriumId){
        auditoriumService.deleteAuditoriumForCinema(auditoriumId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/updateAuditoriumForCinema/{auditoriumId}")
    public void updateAuditoriumForCinema(@PathVariable int auditoriumId, @RequestBody AuditoriumCreationRequest request){
        auditoriumService.updateAuditoriumForCinema(auditoriumId,request);
    }
    
}
