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
    public List<AddressResponse> getAll() {
        List<Address> addressList = addressRepository.findAll();
        return addressList.stream().map(address -> addressMapper.toAddressResponse(address)).collect(Collectors.toList());
    }
}
