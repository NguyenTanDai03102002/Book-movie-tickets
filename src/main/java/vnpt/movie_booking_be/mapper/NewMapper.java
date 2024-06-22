package vnpt.movie_booking_be.mapper;

import org.mapstruct.Mapper;
import vnpt.movie_booking_be.dto.response.NewResponse;
import vnpt.movie_booking_be.models.New;

@Mapper(componentModel = "spring")
public interface NewMapper {
    NewResponse toNewResponse(New n);
}
