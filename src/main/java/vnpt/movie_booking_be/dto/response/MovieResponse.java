package vnpt.movie_booking_be.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    int id;
    String title;
    String description;
    String image;
    String trailer;
    String director;
    Set<String> casts;
    int duration;
    float rating;
    LocalDate release_date;
    LocalDate end_date;
    Set<GenreResponse> genres;
}
