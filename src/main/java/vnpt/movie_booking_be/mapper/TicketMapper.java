package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vnpt.movie_booking_be.dto.response.*;
import vnpt.movie_booking_be.models.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(source = "user.id", target = "userid") // Corrected mapping
    @Mapping(source = "orderTime", target = "orderTime")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "screening.movie.title", target = "movieTitle")
    @Mapping(source = "screening.date", target = "screeningDate")
    @Mapping(source = "screening.start", target = "screeningStartTime") @Mapping(source = "screening.auditorium.name", target = "auditoriumName")
    @Mapping(source = "seats", target = "seats", qualifiedByName = "mapSeats")
   // @Mapping(source = "screening.auditorium", target = "auditorium", qualifiedByName = "mapAuditorium")
    //@Mapping(source = "screening.auditorium.cinema", target = "cinema", qualifiedByName = "mapCinema")
   // @Mapping(source = "screening.auditorium.cinema.address", target = "address", qualifiedByName = "mapAddress")
    @Mapping(source = "screening", target = "screening", qualifiedByName = "mapScreening")
    TicketResponse toTicketResponse(Ticket ticket);

    @Named("mapSeats")
    default List<SeatResponse> mapSeats(Set<Seat> seats) {
        return seats.stream()
                .map(this::toSeatResponse)
                .collect(Collectors.toList());
    }

    default SeatResponse toSeatResponse(Seat seat) {
        return SeatResponse.builder()
                .id(seat.getId())
                .number_Seat(seat.getNumber_Seat())
                .row_Seat(seat.getRow_Seat())
                .seatType(seat.getSeatType())
                .price(seat.getPrice())
                .auditorium(toAuditoriumResponse(seat.getAuditorium()))
                .build();
    }

    @Named("mapAuditorium")
    default AuditoriumResponse toAuditoriumResponse(Auditorium auditorium) {
        return AuditoriumResponse.builder()
                .id(auditorium.getId())
                .name(auditorium.getName())
                .numberSeat(auditorium.getSeats().size())
                .cinema(toCinemaResponse(auditorium.getCinema()))
                .build();
    }

    @Named("mapCinema")
    default CinemaResponse toCinemaResponse(Cinema cinema) {
        return CinemaResponse.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .address(toAddressResponse(cinema.getAddress()))
                .build();
    }

    @Named("mapAddress")
    default AddressResponse toAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .city(address.getCity())
                .ward(address.getWard())
                .street(address.getStreet())
                .district(address.getDistrict())
                .build();
    }

    @Named("mapScreening")
    default ScreeningResponse toScreeningResponse(Screening screening) {
        return ScreeningResponse.builder()
                .id(screening.getId())
                .start(screening.getStart())
                .date(screening.getDate())
                .auditorium(toAuditoriumResponse(screening.getAuditorium()))
                .movie(toMovieResponse(screening.getMovie()))
                .build();
    }

    @Named("mapMovie")
    default MovieResponse toMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .duration(movie.getDuration())
                .image(movie.getImage())
                .director(movie.getDirector())
                .release_date(movie.getRelease_date())
                .end_date(movie.getEnd_date())
                .rating(movie.getRating())
                .trailer(movie.getTrailer())
                .genres(mapGenres(movie.getGenres()))
                .casts(movie.getCasts())
                .build();
    }

    @Named("mapGenres")
    default List<GenreResponse> mapGenres(Set<Genre> genres) {
        return genres.stream()
                .map(this::toGenreResponse)
                .collect(Collectors.toList());
    }

    default GenreResponse toGenreResponse(Genre genre) {
        return GenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
