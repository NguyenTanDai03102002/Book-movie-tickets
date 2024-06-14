package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import vnpt.movie_booking_be.dto.response.SeatResponse;
import vnpt.movie_booking_be.models.Seat;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    SeatResponse toSeatResponse(Seat seat);
}
