package vnpt.movie_booking_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SeatTicketResponse {
    private int id;
    private int numberSeat;
    private String rowSeat;
   // private float price;

}
