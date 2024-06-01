package vnpt.movie_booking_be.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vnpt.movie_booking_be.dto.response.AddressResponse;
import vnpt.movie_booking_be.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAddressHasCinema")
    public List<AddressResponse> getAddressHasCinema() {
        return addressService.getAddressHasCinema();
    }
}
