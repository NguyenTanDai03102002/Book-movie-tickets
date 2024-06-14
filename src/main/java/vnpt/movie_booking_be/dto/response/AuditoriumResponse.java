package vnpt.movie_booking_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vnpt.movie_booking_be.models.Cinema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditoriumResponse {
     int id;
     String name;
     CinemaResponse cinema;
     int numberSeat;
}
