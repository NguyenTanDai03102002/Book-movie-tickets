package vnpt.movie_booking_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vnpt.movie_booking_be.models.DayType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FactorResponse {
     int id;
     DayType dayType;
     float factor;
}
