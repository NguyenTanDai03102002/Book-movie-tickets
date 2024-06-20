package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vnpt.movie_booking_be.dto.response.SeatResponse;
import vnpt.movie_booking_be.dto.response.TicketResponse;
import vnpt.movie_booking_be.models.Seat;
import vnpt.movie_booking_be.models.Ticket;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "screening.movie.title", target = "movieTitle")
    @Mapping(source = "screening.date", target = "screeningDate")
    @Mapping(source = "screening.start", target = "screeningStartTime")
    @Mapping(source = "screening.auditorium.name", target = "auditoriumName")
    @Mapping(source = "seats", target = "seats", qualifiedByName = "mapSeats")
    TicketResponse toTicketResponse(Ticket ticket);

    @Named("mapSeats")
    default List<SeatResponse> mapSeats(Set<Seat> seats) {
        return seats.stream()
                .map(this::toSeatResponse)
                .collect(Collectors.toList());
    }

    default SeatResponse toSeatResponse(Seat seat) {
        return SeatResponse.builder()
                .id(seat.getId())
                .number_Seat(seat.getNumber_Seat())
                .row_Seat(seat.getRow_Seat())
                .build();
    }
}
