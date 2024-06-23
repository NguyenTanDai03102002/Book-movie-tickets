package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vnpt.movie_booking_be.dto.request.CinemaCreationRequest;
import vnpt.movie_booking_be.dto.response.CinemaResponse;
import vnpt.movie_booking_be.models.Cinema;

@Mapper(componentModel = "spring", uses = {AuditoriumMapper.class})
public interface CinemaMapper {
    Cinema toCinema(CinemaCreationRequest request);

    void updateCinema(@MappingTarget Cinema cinema,CinemaCreationRequest request);

    @Mapping(source = "auditoriums", target = "auditoriumResponse")
    CinemaResponse toCinemaResponse(Cinema cinema);
}
