package vnpt.movie_booking_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vnpt.movie_booking_be.models.SeatType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatResponse {
     int id;
     int number_Seat;
     String row_Seat;
     double price;
     AuditoriumResponse auditorium;
     SeatType seatType;
}
