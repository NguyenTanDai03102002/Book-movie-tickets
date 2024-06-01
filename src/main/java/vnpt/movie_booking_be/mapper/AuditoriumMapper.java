package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import vnpt.movie_booking_be.dto.response.AuditoriumResponse;
import vnpt.movie_booking_be.models.Auditorium;

@Mapper(componentModel = "spring")
public interface AuditoriumMapper {
    AuditoriumResponse toAuditoriumResponse(Auditorium auditorium);
}
