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

    private String movieTitle;
    private String qrcode;

    private LocalDate screeningDate;
    private LocalTime screeningStartTime;

    private String auditoriumName;
    private int total;

    private List<SeatResponse> seats;
private ScreeningResponse screening;
    private Date orderTime;
    private int userid;
    private VourcherRespone vourcher;
}


