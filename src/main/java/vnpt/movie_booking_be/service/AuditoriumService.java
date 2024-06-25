package vnpt.movie_booking_be.service;

import vnpt.movie_booking_be.dto.request.AuditoriumCreationRequest;
import vnpt.movie_booking_be.dto.response.AuditoriumResponse;
import vnpt.movie_booking_be.models.Auditorium;

import java.util.List;

public interface AuditoriumService {
    List<AuditoriumResponse> getAll();

    List<AuditoriumResponse> getAuditoriumByCinema(int cinemaId);

    void createAuditoriumForCinema(int cinemaId, AuditoriumCreationRequest request);

    void deleteAuditoriumForCinema(int auditoriumId);

    void updateAuditoriumForCinema(int auditoriumId, AuditoriumCreationRequest request);
}
