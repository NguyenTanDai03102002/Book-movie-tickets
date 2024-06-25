package vnpt.movie_booking_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"genres","screenings","reviews"})
@ToString(exclude = {"genres","screenings","reviews"})
@Entity
public class Movie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
    private String trailer;
    private String director;
    private int number_comment;
    @ElementCollection
    private Set<String> casts;
    private int duration;
    private double rating;
    private LocalDate release_date;
    private LocalDate end_date;

private boolean isActive;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", orphanRemoval = true)
    private List<Screening> screenings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie",orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

}