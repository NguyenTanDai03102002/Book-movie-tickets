package vnpt.movie_booking_be.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditoriumCreationRequest {
    String name;
    int normal;
    float normalPrice;
    int vip;
    float vipPrice;
    int sweetBox;
    float sweetBoxPrice;
}
