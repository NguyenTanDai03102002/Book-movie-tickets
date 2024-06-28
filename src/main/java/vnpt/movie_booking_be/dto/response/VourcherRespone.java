package vnpt.movie_booking_be.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Date;


@Data

@AllArgsConstructor
@Builder
public class VourcherRespone {

    private int id;
    private Date startDateTime;
    private Date endDateTime;
    private int number;
    private double discount;
    private String content;

    // Constructor không tham số
    public VourcherRespone() {}

}
