package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.response.VourcherRespone;
import vnpt.movie_booking_be.mapper.VourcherMapper;
import vnpt.movie_booking_be.models.Vourcher;
import vnpt.movie_booking_be.repository.VourcherRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
@Service
public class VourcherServiceimpl implements VourcherService {
    @Autowired
    private VourcherRepository vourcherRepository;
    @Autowired
    private VourcherMapper vourcherMapper;

    @Override
    public Vourcher addVourcher(Vourcher vourcher) {
        return vourcherRepository.save(vourcher);
    }

    @Override
    public Vourcher updateVourcher(int id, Vourcher vourcherDetails) {
        Optional<Vourcher> optionalVourcher = vourcherRepository.findById(id);
        if (optionalVourcher.isPresent()) {
            Vourcher vourcher = optionalVourcher.get();
            vourcher.setStartDateTime(vourcherDetails.getStartDateTime());
            vourcher.setEndDateTime(vourcherDetails.getEndDateTime());
            vourcher.setNumber(vourcherDetails.getNumber());
            vourcher.setDiscount(vourcherDetails.getDiscount());
            vourcher.setContent(vourcherDetails.getContent());
            return vourcherRepository.save(vourcher);
        }
        return null;
    }

    @Override
    public void decrementNumber(int id) {
        Optional<Vourcher> optionalVourcher = vourcherRepository.findById(id);
        if (optionalVourcher.isPresent()) {
            Vourcher vourcher = optionalVourcher.get();
            vourcher.setNumber(vourcher.getNumber() - 1);
            vourcherRepository.save(vourcher);
        }
    }

    @Override
    public VourcherRespone editVourcher(int id, Vourcher vourcherDetails) {
        Optional<Vourcher> optionalVourcher = vourcherRepository.findById(id);
        if (optionalVourcher.isPresent()) {
            Vourcher vourcher = optionalVourcher.get();
            vourcher.setStartDateTime(vourcherDetails.getStartDateTime());
            vourcher.setEndDateTime(vourcherDetails.getEndDateTime());
            vourcher.setNumber(vourcherDetails.getNumber());
            vourcher.setDiscount(vourcherDetails.getDiscount());
            vourcher.setContent(vourcherDetails.getContent());
            Vourcher updatedVourcher = vourcherRepository.save(vourcher);

            VourcherRespone vourcherRespone = new VourcherRespone();
            vourcherRespone.setId(updatedVourcher.getId());
            vourcherRespone.setStartDateTime(updatedVourcher.getStartDateTime());
            vourcherRespone.setEndDateTime(updatedVourcher.getEndDateTime());
            vourcherRespone.setNumber(updatedVourcher.getNumber());
            vourcherRespone.setDiscount(updatedVourcher.getDiscount());
            vourcherRespone.setContent(updatedVourcher.getContent());

            return vourcherRespone;
        }
        return null;
    }
    @Override
    public List<VourcherRespone> getAllVourchers() {
        return vourcherRepository.findAll().stream()
                .map(vourcherMapper::toRespone)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VourcherRespone> getVourcherById(int id) {
        Optional<Vourcher> vourcher = vourcherRepository.findById(id);
        return vourcher.map(vourcherMapper::toRespone);
    }
}
