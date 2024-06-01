package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import vnpt.movie_booking_be.dto.response.ScreeningResponse;
import vnpt.movie_booking_be.models.Screening;

@Mapper(componentModel = "spring")
public interface ScreeningMapper {
    ScreeningResponse toScreeningResponse(Screening screening);
}
