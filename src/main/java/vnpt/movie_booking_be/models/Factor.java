package vnpt.movie_booking_be.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"cinema"})
@ToString(exclude = {"cinema"})
@Entity
public class Factor {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 @Enumerated(EnumType.STRING)
	 private DayType dayType;
	 
	 private float factor;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "cinema_id")
	 private Cinema cinema;
}
