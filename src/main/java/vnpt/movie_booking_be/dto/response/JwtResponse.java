package vnpt.movie_booking_be.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {
     String token;

     UserResponse user;
}
