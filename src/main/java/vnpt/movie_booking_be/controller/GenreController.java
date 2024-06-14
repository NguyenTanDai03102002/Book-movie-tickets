package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.request.GenreCreationRequest;
import vnpt.movie_booking_be.dto.response.GenreResponse;
import vnpt.movie_booking_be.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/genre")
@CrossOrigin("*")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/get/getAll")
    public List<GenreResponse> GetALl() {
        return genreService.getAll();
    }

    @GetMapping("/get/getPage")
    public Page<GenreResponse> GetAllGenrePageable(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(required =false) String keyword) {
        return genreService.getAllGenrePageable(PageRequest.of(page,size),keyword);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/createGenre")
    public void createGenre(@RequestBody GenreCreationRequest request) {
        genreService.createGenre(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/deleteGenre/{genreId}")
    public void deleteGenre(@PathVariable int genreId) {
        genreService.deleteGenre(genreId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/updateGenre/{genreId}")
    public void updateGenre(@PathVariable int genreId,@RequestBody GenreCreationRequest request) {
        genreService.updateGenre(genreId,request);
    }
}
