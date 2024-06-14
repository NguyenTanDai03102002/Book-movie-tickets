package vnpt.movie_booking_be.dto.response;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
=======
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
import lombok.*;
import lombok.experimental.FieldDefaults;
import vnpt.movie_booking_be.models.PaymentMethod;

<<<<<<< HEAD

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class TicketResponse {
    private String userName;
    private int movieId;
    private String movieTitle;
    private String qrcode; // Thêm trường này
    private int screeningId;
    private LocalDate screeningDate;
    private LocalTime screeningStartTime;
    private int auditoriumId;
    private String auditoriumName;
    private int total;
    private String rowSeat;
    private int numberSeat;
}


=======
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
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
