package vnpt.movie_booking_be.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
<<<<<<< HEAD

=======
>>>>>>> db7b916ecd5572526930ee42217ce8956903eb0f
    @GetMapping("/cities")
    public ResponseEntity<List<String>> getCities() {
        List<String> cities = addressService.getDistinctCities();
        return ResponseEntity.ok(cities);
    }

    // API để lấy danh sách các quận/huyện trong một thành phố
    @GetMapping("/districts")
    public ResponseEntity<List<String>> getDistricts(@RequestParam String city) {
        List<String> districts = addressService.getDistrictsByCity(city);
        return ResponseEntity.ok(districts);
    }

    // API để lấy danh sách các phường/xã trong một quận/huyện của thành phố
    @GetMapping("/wards")
    public ResponseEntity<List<String>> getWards(@RequestParam String city, @RequestParam String district) {
        List<String> wards = addressService.getWardsByCityAndDistrict(city, district);
        return ResponseEntity.ok(wards);
    }

    // API để lấy danh sách các đường/phố trong một phường/xã của quận/huyện và thành phố đã chọn
    @GetMapping("/streets")
    public ResponseEntity<List<String>> getStreets(@RequestParam String city, @RequestParam String district, @RequestParam String ward) {
        List<String> streets = addressService.getStreetsByCityDistrictAndWard(city, district, ward);
        return ResponseEntity.ok(streets);
    }
}
