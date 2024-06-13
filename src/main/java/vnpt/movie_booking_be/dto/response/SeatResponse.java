package vnpt.movie_booking_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatResponse {
     int id;
     int number_Seat;
     String row_Seat;
     float price;
     AuditoriumResponse auditorium;
int seatstatus;
}
