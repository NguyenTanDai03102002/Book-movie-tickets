package vnpt.movie_booking_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.*;


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


