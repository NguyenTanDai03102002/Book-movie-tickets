package vnpt.movie_booking_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {
    // private int id;
    int total;
    Date orderTime;
    int status;
  //  List<Integer> seat;
    Set<SeatResponse> seat;
    int screeningid;
    int userid;
    int movieid;


}
