package vnpt.movie_booking_be.service;

import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.response.AddressResponse;

import java.util.List;
@Service
public interface AddressService {
    List<AddressResponse> getAddressHasCinema();
    List<String> getDistinctCities();

    List<String> getDistrictsByCity(String city);

    List<String> getWardsByCityAndDistrict(String city, String district);

    List<String> getStreetsByCityDistrictAndWard(String city, String district, String ward);
}
