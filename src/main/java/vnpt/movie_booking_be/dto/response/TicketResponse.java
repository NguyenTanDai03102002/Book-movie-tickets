package vnpt.movie_booking_be.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vnpt.movie_booking_be.models.PaymentMethod;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketResponse {
     int id;

     int total;

     Date orderTime;

     int status;

    @Enumerated(EnumType.STRING)
     PaymentMethod paymentMethod;
}
