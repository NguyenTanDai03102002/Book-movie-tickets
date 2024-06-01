package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vnpt.movie_booking_be.dto.request.GenreCreationRequest;
import vnpt.movie_booking_be.dto.response.GenreResponse;
import vnpt.movie_booking_be.models.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre toGenre(GenreCreationRequest request);

    GenreResponse toGenreResponse(Genre genre);

    void updateGenre(@MappingTarget  Genre genre, GenreCreationRequest request);
}
