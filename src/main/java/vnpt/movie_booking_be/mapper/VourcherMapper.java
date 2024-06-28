package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import vnpt.movie_booking_be.dto.response.VourcherRespone;
import vnpt.movie_booking_be.models.Vourcher;
@Mapper(componentModel = "spring")
public interface VourcherMapper {
    VourcherRespone toRespone(Vourcher voucher);
}
