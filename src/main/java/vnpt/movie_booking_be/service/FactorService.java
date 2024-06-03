package vnpt.movie_booking_be.service;

import vnpt.movie_booking_be.dto.response.FactorResponse;

import java.util.List;

public interface FactorService {
    List<FactorResponse> getAllFactorByCinema(int cinemaId);
}
