package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import vnpt.movie_booking_be.dto.response.TicketResponse;
import vnpt.movie_booking_be.models.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketResponse toTicketResponse(Ticket ticket);
}
