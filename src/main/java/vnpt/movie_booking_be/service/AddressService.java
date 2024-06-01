package vnpt.movie_booking_be.service;

import vnpt.movie_booking_be.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getAddressHasCinema();
}
