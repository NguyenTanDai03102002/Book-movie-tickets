package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.response.AddressResponse;
import vnpt.movie_booking_be.mapper.AddressMapper;
import vnpt.movie_booking_be.models.Address;
import vnpt.movie_booking_be.repository.AddressRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<AddressResponse> getAddressHasCinema() {
        List<Address> addresses = addressRepository.findAddressHasCinema();
        return addresses.stream().map(address -> addressMapper.toAddressResponse(address))
                .collect(Collectors.toList());
    }
    @Override
    public List<String> getDistinctCities() {
        return addressRepository.findDistinctCities();
    }
    @Override
    public List<String> getDistrictsByCity(String city) {
        return addressRepository.findDistrictsByCity(city);
    }
    @Override
    public List<String> getWardsByCityAndDistrict(String city, String district) {
        return addressRepository.findWardsByCityAndDistrict(city, district);
    }
    @Override
    public List<String> getStreetsByCityDistrictAndWard(String city, String district, String ward) {
        return addressRepository.findStreetsByCityDistrictAndWard(city, district, ward);
    }
}
