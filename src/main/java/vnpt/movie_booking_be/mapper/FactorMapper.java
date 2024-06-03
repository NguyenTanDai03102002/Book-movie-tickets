package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import vnpt.movie_booking_be.dto.response.FactorResponse;
import vnpt.movie_booking_be.models.Factor;

@Mapper(componentModel = "spring")
public interface FactorMapper {
    FactorResponse toCinemaResponse(Factor factor);
}
