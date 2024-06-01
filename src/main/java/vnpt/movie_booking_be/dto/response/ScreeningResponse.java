package vnpt.movie_booking_be.dto.response;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vnpt.movie_booking_be.models.Auditorium;
import vnpt.movie_booking_be.models.Movie;

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
