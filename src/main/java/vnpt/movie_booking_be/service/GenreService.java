package vnpt.movie_booking_be.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import vnpt.movie_booking_be.dto.request.GenreCreationRequest;
import vnpt.movie_booking_be.dto.response.GenreResponse;

import java.util.List;

public interface GenreService {

    void createGenre(GenreCreationRequest request);

    List<GenreResponse> getAll();

    void deleteGenre(int genreId);

    void updateGenre(int genreId, GenreCreationRequest request);

    Page<GenreResponse> getAllGenrePageable(Pageable pageable, String keyword);
}
