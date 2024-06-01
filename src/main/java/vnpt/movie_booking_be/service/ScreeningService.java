package vnpt.movie_booking_be.service;

import vnpt.movie_booking_be.dto.response.ScreeningResponse;

import java.util.List;

public interface ScreeningService {
    List<ScreeningResponse> getScreeningByCityAndMovie(String city, int movieId);
}
