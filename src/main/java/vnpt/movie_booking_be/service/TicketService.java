package vnpt.movie_booking_be.service;

import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.response.TicketResponse;

import java.util.List;
@Service
public interface TicketService {
    List<TicketResponse> getTicketByYear(int year);

    List<TicketResponse> getAllTickets();
}
