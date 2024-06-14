package vnpt.movie_booking_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"cinema","screenings","seats"})
@ToString(exclude = {"cinema","screenings","seats"})
@Entity
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screening> screenings = new ArrayList<>();

    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

}
