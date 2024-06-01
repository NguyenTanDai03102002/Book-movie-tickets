package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.request.CinemaCreationRequest;
import vnpt.movie_booking_be.dto.response.CinemaResponse;
import vnpt.movie_booking_be.mapper.CinemaMapper;
import vnpt.movie_booking_be.models.Address;
import vnpt.movie_booking_be.models.Cinema;
import vnpt.movie_booking_be.repository.AddressRepository;
import vnpt.movie_booking_be.repository.CinemaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CinemaMapper cinemaMapper;

    @Override
    public void createCinema(CinemaCreationRequest request) {
        Cinema cinema = cinemaRepository.findByName(request.getName());
        if(cinema == null) {
            cinema = cinemaMapper.toCinema(request);
            cinemaRepository.save(cinema);
        }else{
            throw new RuntimeException("Cinema already exists");
        }
    }

    @Override
    public List<CinemaResponse> getCinemasByCity(String city) {
        List<Cinema> cinemaList = cinemaRepository.findCinemasByAddress(city);
        return cinemaList.stream().map(cinema -> cinemaMapper.toCinemaResponse(cinema)).collect(Collectors.toList());
    }

    @Override
    public void deleteCinema(int cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new RuntimeException("Cinema not found"));
        cinemaRepository.delete(cinema);
    }

    @Override
    public void updateCinema(int cinemaId,CinemaCreationRequest request) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new RuntimeException("Cinema not found"));
        cinemaMapper.updateCinema(cinema,request);
        cinemaRepository.save(cinema);
    }

    @Override
    public List<CinemaResponse> getAllCinema() {
        List<Cinema> cinemaList = cinemaRepository.findAll();
        return cinemaList.stream().map(cinema -> cinemaMapper.toCinemaResponse(cinema))
                .collect(Collectors.toList());
    }


}
