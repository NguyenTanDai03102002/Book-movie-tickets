package vnpt.movie_booking_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vnpt.movie_booking_be.models.Address;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaResponse {
     int id;
     String name;
     AddressResponse address;
     Set<AuditoriumResponse> auditoriums;
     Set<FactorResponse> factors;
}
