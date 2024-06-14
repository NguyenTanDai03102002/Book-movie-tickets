package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.request.AuditoriumCreationRequest;
import vnpt.movie_booking_be.dto.response.AuditoriumResponse;
import vnpt.movie_booking_be.mapper.AuditoriumMapper;
import vnpt.movie_booking_be.models.Auditorium;
import vnpt.movie_booking_be.models.Cinema;
import vnpt.movie_booking_be.models.Seat;
import vnpt.movie_booking_be.repository.AuditoriumRepository;
import vnpt.movie_booking_be.repository.CinemaRepository;
import vnpt.movie_booking_be.repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private AuditoriumMapper auditoriumMapper;

    @Override
    public List<AuditoriumResponse> getAll() {
        List<Auditorium> auditoriumList = auditoriumRepository.findAll();
        List<AuditoriumResponse> auditoriumResponseList = new ArrayList<>();
        for (Auditorium auditorium : auditoriumList) {
            AuditoriumResponse auditoriumResponse = auditoriumMapper.toAuditoriumResponse(auditorium);
            auditoriumResponse.setNumberSeat(auditorium.getSeats().size());
            auditoriumResponseList.add(auditoriumResponse);

        }
        return auditoriumResponseList;
    }

    @Override
    public List<AuditoriumResponse> getAuditoriumByCinema(int cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new RuntimeException("cinema not found"));
        List<Auditorium> auditoriumList = auditoriumRepository.findAuditoriumsByCinema(cinema);
        List<AuditoriumResponse> auditoriumResponseList = new ArrayList<>();
        for (Auditorium auditorium : auditoriumList) {
            AuditoriumResponse auditoriumResponse = auditoriumMapper.toAuditoriumResponse(auditorium);
            auditoriumResponse.setNumberSeat(auditorium.getSeats().size());
            auditoriumResponseList.add(auditoriumResponse);

        }
        return auditoriumResponseList;
    }

    @Override
    public void createAuditoriumForCinema(int cinemaId, AuditoriumCreationRequest request) {
        if(auditoriumRepository.findByName(request.getName()) != null){
            throw  new RuntimeException("Auditorium already exists");
        }
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new RuntimeException("cinema not found"));
        Auditorium auditorium = auditoriumRepository.save(Auditorium.builder()
                .name(request.getName())
                .cinema(cinema)
                .build());

        int totalSeats = request.getNormal() + request.getVip() + request.getSweetBox();
        int seatsPerRow = 10;
        char currentRow = 'A';

        for (int i = 1; i <= totalSeats; i++) {
            float price;
            if (i <= request.getNormal()) {
                price = request.getNormalPrice();
            } else if (i <= request.getNormal() + request.getVip()) {
                price = request.getVipPrice();
            } else {
                price = request.getSweetBoxPrice();
            }

            seatRepository.save(Seat.builder()
                    .number_Seat(i)
                    .row_Seat(Character.toString(currentRow))
                    .price(price)
                    .auditorium(auditorium)
                    .build());

            if (i % seatsPerRow == 0) {
                currentRow++;
            }
        }
    }

}
