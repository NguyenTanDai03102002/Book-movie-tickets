package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
<<<<<<< HEAD

=======
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
import vnpt.movie_booking_be.dto.response.TicketResponse;
import vnpt.movie_booking_be.models.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketResponse toTicketResponse(Ticket ticket);
}
