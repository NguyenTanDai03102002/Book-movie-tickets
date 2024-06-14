package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import vnpt.movie_booking_be.dto.response.AddressResponse;
import vnpt.movie_booking_be.models.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponse toAddressResponse(Address address);
}
