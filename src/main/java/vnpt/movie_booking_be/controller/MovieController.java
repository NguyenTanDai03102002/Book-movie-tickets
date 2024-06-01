package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vnpt.movie_booking_be.dto.response.MovieResponse;
import vnpt.movie_booking_be.models.Movie;
import vnpt.movie_booking_be.service.MovieService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movie")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getAll")
    public List<MovieResponse> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/create")
    public void createMovie(@RequestParam("title") String title,
                             @RequestParam("description") String description,
                             @RequestPart("file") MultipartFile file,
                             @RequestPart("video") MultipartFile video,
                             @RequestParam("director") String director,
                             @RequestParam("casts") String casts,
                             @RequestParam("duration") String duration,
                             @RequestParam("rating") float rating,
                             @RequestParam("releaseDate") String releaseDate,
                             @RequestParam("endDate") String endDate,
                             @RequestParam("genreIds") Set<Integer> genreIds) {

        movieService.createMovie(title, genreIds, description, file, video,
                director, casts, duration, rating, releaseDate, endDate);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestParam String title,
                                             @RequestParam Set<Integer> genreIds, @RequestParam String description,
                                             @RequestParam MultipartFile file, @RequestParam MultipartFile video,
                                             @RequestParam String director, @RequestParam String casts,
                                             @RequestParam String duration, @RequestParam String rating,
                                             @RequestParam String releaseDate, @RequestParam String endDate) {
        Movie updatedMovie = movieService.updateMovie(id, title, genreIds, description, file, video,
                director, casts, duration, rating,
                releaseDate, endDate);
        return ResponseEntity.ok().body(updatedMovie);
    }
}
