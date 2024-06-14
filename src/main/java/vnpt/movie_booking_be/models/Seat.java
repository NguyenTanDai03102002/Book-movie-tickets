package vnpt.movie_booking_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"ticket","auditorium"})
@ToString(exclude = {"ticket","auditorium"})
@Entity
public class Seat{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number_Seat;
    private String row_Seat;
    private float price;
<<<<<<< HEAD
private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
=======
    
    @OneToMany(mappedBy = "seat" , cascade = CascadeType.ALL , orphanRemoval = true)
   	private Set<Ticket> ticket =  new HashSet<>();
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;
}
