package vnpt.movie_booking_be.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import vnpt.movie_booking_be.dto.request.CinemaCreationRequest;
import vnpt.movie_booking_be.dto.response.CinemaResponse;

import java.util.List;

public interface CinemaService {
    void createCinema(CinemaCreationRequest request);

    void deleteCinema(int cinemaId);

    void updateCinema(int cinemaId,CinemaCreationRequest request);

    List<CinemaResponse> getAllCinema();

    List<CinemaResponse> getCinemasByCity(String city);

    List<CinemaResponse> GetAllCinemaPageable(Pageable pageable, String keyword);
}
