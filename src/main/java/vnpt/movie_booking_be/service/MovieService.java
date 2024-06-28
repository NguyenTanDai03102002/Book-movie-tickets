package vnpt.movie_booking_be.service;

import org.springframework.web.multipart.MultipartFile;
import vnpt.movie_booking_be.dto.response.MovieResponse;
import vnpt.movie_booking_be.models.Movie;

import java.util.List;
import java.util.Set;

public interface MovieService {
    List<MovieResponse> getAllMovies();

    List<MovieResponse> getAllMoviesDelete();

    void createMovie(String title, Set<Integer> genreIds, String description, MultipartFile file, MultipartFile video, String director, String casts, String duration, float rating, String releaseDate, String endDate);

    Movie updateMovie(Integer id, String title, Set<Integer> genreIds, String description, MultipartFile file, MultipartFile video, String director, String casts, String duration, String rating, String releaseDate, String endDate, boolean isActive);
}
