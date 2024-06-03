package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.response.FactorResponse;
import vnpt.movie_booking_be.mapper.CinemaMapper;
import vnpt.movie_booking_be.mapper.FactorMapper;
import vnpt.movie_booking_be.models.Cinema;
import vnpt.movie_booking_be.models.Factor;
import vnpt.movie_booking_be.repository.CinemaRepository;
import vnpt.movie_booking_be.repository.FactorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactorServiceImpl implements FactorService{
    @Autowired
    private FactorRepository factorRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private FactorMapper factorMapper;


    @Override
    public List<FactorResponse> getAllFactorByCinema(int cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new RuntimeException("Cinema not found"));
        List<Factor> factors = factorRepository.findFactorByCinema(cinema);
        return factors.stream().map(factor -> factorMapper.toCinemaResponse(factor))
                .collect(Collectors.toList());
    }
}
