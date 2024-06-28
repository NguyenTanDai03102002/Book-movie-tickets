package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.models.Vourcher;
import vnpt.movie_booking_be.dto.response.VourcherRespone;
import vnpt.movie_booking_be.service.VourcherService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vourchers")
public class VourcherController {

    @Autowired
    private VourcherService vourcherService;

    @PostMapping("/Create")
    public ResponseEntity<Vourcher> addVourcher(@RequestBody Vourcher vourcher) {
        Vourcher createdVourcher = vourcherService.addVourcher(vourcher);
        return ResponseEntity.ok(createdVourcher);
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<Vourcher> updateVourcher(@PathVariable int id, @RequestBody Vourcher vourcherDetails) {
        Vourcher updatedVourcher = vourcherService.updateVourcher(id, vourcherDetails);
        if (updatedVourcher != null) {
            return ResponseEntity.ok(updatedVourcher);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/DiscountNumber/{id}")
    public ResponseEntity<Void> decrementNumber(@PathVariable int id) {
        vourcherService.decrementNumber(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<VourcherRespone> editVourcher(@PathVariable int id, @RequestBody Vourcher vourcherDetails) {
        VourcherRespone vourcherRespone = vourcherService.editVourcher(id, vourcherDetails);
        if (vourcherRespone != null) {
            return ResponseEntity.ok(vourcherRespone);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/GetAll")
    public ResponseEntity<List<VourcherRespone>> getAllVourchers() {
        List<VourcherRespone> vourchers = vourcherService.getAllVourchers();
        return ResponseEntity.ok(vourchers);
    }

    @GetMapping("/GetbyId/{id}")
    public ResponseEntity<VourcherRespone> getVourcherById(@PathVariable int id) {
        Optional<VourcherRespone> vourcher = vourcherService.getVourcherById(id);
        return vourcher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
