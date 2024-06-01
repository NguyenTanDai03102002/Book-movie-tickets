package vnpt.movie_booking_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScreeningResponse {
     int id;
     LocalTime start;
     LocalDate date;
     AuditoriumResponse auditorium;
     MovieResponse movie;
}
