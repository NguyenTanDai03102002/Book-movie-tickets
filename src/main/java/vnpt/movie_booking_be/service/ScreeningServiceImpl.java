package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.request.ScreeningCreationRequest;
import vnpt.movie_booking_be.dto.response.ScreeningResponse;
import vnpt.movie_booking_be.mapper.ScreeningMapper;
import vnpt.movie_booking_be.models.Auditorium;
import vnpt.movie_booking_be.models.Movie;
import vnpt.movie_booking_be.models.Screening;
import vnpt.movie_booking_be.repository.AuditoriumRepository;
import vnpt.movie_booking_be.repository.MovieRepository;
import vnpt.movie_booking_be.repository.ScreeningRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImpl implements ScreeningService{

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreeningMapper screeningMapper;

    @Override
    public List<ScreeningResponse> getScreeningByCinema(int cinemaId) {
        List<Screening> screeningList = screeningRepository.findScreeningsByCinema(cinemaId);
        return screeningList.stream().map(screening -> screeningMapper.toScreeningResponse(screening))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScreeningResponse> getScreeningByCityAndMovie(String city, int movieId) {
        List<Screening> screeningList = screeningRepository.findScreeningsByCityAndMovie(city,movieId);
        return screeningList.stream().map(screening -> screeningMapper.toScreeningResponse(screening))
                .collect(Collectors.toList());
    }

    @Override
    public void createScreening(ScreeningCreationRequest request) {
        Auditorium auditorium = auditoriumRepository.findById(request.getAuditoriumId()).orElseThrow(()->new RuntimeException("Auditorium not found"));
        Movie movie = movieRepository.findById(request.getMovieId()).orElseThrow(()->new RuntimeException("Movie not found"));
        screeningRepository.save(Screening.builder()
                        .date(request.getDate())
                        .start(request.getStart())
                        .auditorium(auditorium)
                        .movie(movie)
                .build());
    }

    @Override
    public void updateScreening(int screeningId, ScreeningCreationRequest request) {
        Screening screening = screeningRepository.findById(screeningId).orElseThrow(()->new RuntimeException("Screening not found"));
        Auditorium auditorium = auditoriumRepository.findById(request.getAuditoriumId()).orElseThrow(()->new RuntimeException("Auditorium not found"));
        Movie movie = movieRepository.findById(request.getMovieId()).orElseThrow(()->new RuntimeException("Movie not found"));
        screening.setStart(request.getStart());
        screening.setDate(request.getDate());
        screening.setAuditorium(auditorium);
        screening.setMovie(movie);
        screeningRepository.save(screening);
    }

    @Override
    public void deleteScreening(int screeningId) {
        Screening screening = screeningRepository.findById(screeningId).orElseThrow(()->new RuntimeException("Screening not found"));
        screeningRepository.delete(screening);
    }

    @Override
    public List<ScreeningResponse> getAll() {
        List<Screening> screeningList = screeningRepository.findAll();
        return screeningList.stream().map(screening -> screeningMapper.toScreeningResponse(screening))
                .collect(Collectors.toList());
    }

}
