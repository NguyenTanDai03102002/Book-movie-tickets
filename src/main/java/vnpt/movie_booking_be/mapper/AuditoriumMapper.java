package vnpt.movie_booking_be.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vnpt.movie_booking_be.dto.response.AuditoriumResponse;
import vnpt.movie_booking_be.models.Auditorium;

@Mapper(componentModel = "spring")
public interface AuditoriumMapper {
    AuditoriumResponse toAuditoriumResponse(Auditorium auditorium);
}
