package vnpt.movie_booking_be.service;

import vnpt.movie_booking_be.dto.response.TicketResponse;

import java.util.List;

public interface TicketService {
    List<TicketResponse> getTicketByYear(int year);
}
