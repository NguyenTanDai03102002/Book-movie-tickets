package vnpt.movie_booking_be.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScreeningCreationRequest {
     LocalTime start;
     LocalDate date;
     int auditoriumId;
     int movieId;
}
