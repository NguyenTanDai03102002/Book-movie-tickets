package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.response.ScreeningResponse;
import vnpt.movie_booking_be.mapper.ScreeningMapper;
import vnpt.movie_booking_be.models.Screening;
import vnpt.movie_booking_be.repository.ScreeningRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImpl implements ScreeningService{

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ScreeningMapper screeningMapper;

    @Override
    public List<ScreeningResponse> getScreeningByCityAndMovie(String city, int movieId) {
        List<Screening> screeningList = screeningRepository.findScreeningsByCityAndMovie(city,movieId);
        return screeningList.stream().map(screening -> screeningMapper.toScreeningResponse(screening))
                .collect(Collectors.toList());
    }
}
