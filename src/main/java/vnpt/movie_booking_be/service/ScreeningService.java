package vnpt.movie_booking_be.service;

import vnpt.movie_booking_be.dto.request.ScreeningCreationRequest;
import vnpt.movie_booking_be.dto.response.ScreeningResponse;

import java.util.List;

public interface ScreeningService {
    List<ScreeningResponse> getScreeningByCityAndMovie(String city, int movieId);

    List<ScreeningResponse> getScreeningByCinema(int cinemaId);

    void createScreening(ScreeningCreationRequest request);

    void updateScreening(int screeningId, ScreeningCreationRequest request);

    void deleteScreening(int screeningId);

    List<ScreeningResponse> getAll();
}
