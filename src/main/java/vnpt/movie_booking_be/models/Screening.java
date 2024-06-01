package vnpt.movie_booking_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"auditorium","movie","ticket"})
@ToString(exclude = {"auditorium","movie","ticket"})
@Entity
public class Screening{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDateTime start;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    
    @OneToMany(mappedBy = "screening" , cascade = CascadeType.ALL , orphanRemoval = true)
	private Set<Ticket> ticket =  new HashSet<>();
}
