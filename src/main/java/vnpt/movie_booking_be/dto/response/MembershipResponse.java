package vnpt.movie_booking_be.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MembershipResponse {
     int id;
     String name;
     String description;
     float discount_rate;
}
