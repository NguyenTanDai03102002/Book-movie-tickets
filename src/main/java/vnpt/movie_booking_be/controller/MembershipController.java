package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.response.MembershipResponse;
import vnpt.movie_booking_be.models.Membership;
import vnpt.movie_booking_be.service.VNPayServiceimpl;

import java.util.List;

@RestController
@RequestMapping("/memberships")
public class MembershipController {

    @Autowired
    private VNPayServiceimpl membershipService;

    @PostMapping("/create")
    public MembershipResponse createMembership(@RequestBody Membership membership) {
        return membershipService.createMembership(membership);
    }

    @PutMapping("/{id}")
    public MembershipResponse updateMembership(@PathVariable int id, @RequestBody Membership membershipDetails) {
        return membershipService.updateMembership(id, membershipDetails);
    }

    @GetMapping("/getall")
    public List<MembershipResponse> getAllMemberships() {
        return membershipService.getAllMemberships();
    }

}