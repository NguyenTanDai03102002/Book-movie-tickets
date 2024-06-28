package vnpt.movie_booking_be.service;

import vnpt.movie_booking_be.dto.response.VourcherRespone;
import vnpt.movie_booking_be.models.Vourcher;

import java.util.List;
import java.util.Optional;

public interface VourcherService {
    Vourcher addVourcher(Vourcher vourcher);
    Vourcher updateVourcher(int id, Vourcher vourcherDetails);
    void decrementNumber(int id);
    VourcherRespone editVourcher(int id, Vourcher vourcherDetails);

    List<VourcherRespone> getAllVourchers();

    Optional<VourcherRespone> getVourcherById(int id);
}
