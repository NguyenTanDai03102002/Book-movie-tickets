package vnpt.movie_booking_be.service;

import vnpt.movie_booking_be.dto.response.SeatResponse;

import java.util.List;

public interface SeatService {
    List<SeatResponse> getSeatsById(int seatId);
}
