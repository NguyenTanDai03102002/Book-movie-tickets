package vnpt.movie_booking_be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Builder
public class TicketRequest {

    private int totalPrice;
    private String movieName;
    private String date;
    private String startTime;
    private String auditoriumName;
    private String seatInfo;
    private String userName;

    public TicketRequest(int totalPrice, String movieName, String date, String startTime, String auditoriumName, String seatInfo, String userName) {
        this.totalPrice = totalPrice;
        this.movieName = movieName;
        this.date = date;
        this.startTime = startTime;
        this.auditoriumName = auditoriumName;
        this.seatInfo = seatInfo;
        this.userName = userName;
    }
}
