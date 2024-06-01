package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import vnpt.movie_booking_be.dto.response.MovieResponse;
import vnpt.movie_booking_be.models.Movie;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieResponse movieToMovieResponse(Movie movies);
}
