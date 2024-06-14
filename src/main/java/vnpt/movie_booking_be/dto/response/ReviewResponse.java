package vnpt.movie_booking_be.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {
     int id;
     Date date;
     int numberStar;
     String content;
     UserResponse user;
}
