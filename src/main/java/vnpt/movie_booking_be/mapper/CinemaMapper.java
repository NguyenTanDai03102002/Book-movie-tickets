package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vnpt.movie_booking_be.dto.request.CinemaCreationRequest;
import vnpt.movie_booking_be.dto.response.CinemaResponse;
import vnpt.movie_booking_be.models.Cinema;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    Cinema toCinema(CinemaCreationRequest request);

    void updateCinema(@MappingTarget Cinema cinema,CinemaCreationRequest request);

    CinemaResponse toCinemaResponse(Cinema cinema);
}
