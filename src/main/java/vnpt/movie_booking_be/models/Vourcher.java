package vnpt.movie_booking_be.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Vourcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date startDateTime;
    private Date endDateTime;
    private int number;
    private double discount;
    private String content;

    public Vourcher(Date startDateTime, Date endDateTime, int number, double discount, String content) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.number = number;
        this.discount = discount;
        this.content = content;
    }

    public Vourcher() {

    }
}
