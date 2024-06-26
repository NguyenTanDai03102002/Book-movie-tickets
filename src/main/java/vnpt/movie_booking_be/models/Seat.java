package vnpt.movie_booking_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"ticket", "auditorium"})
@ToString(exclude = {"ticket", "auditorium"})
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number_Seat;
    private String row_Seat;
    private double price;

    @ManyToMany(mappedBy = "seats")
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
