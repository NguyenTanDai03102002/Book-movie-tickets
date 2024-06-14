package vnpt.movie_booking_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.request.ReviewCreationRequest;
import vnpt.movie_booking_be.dto.response.ReviewResponse;
import vnpt.movie_booking_be.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/get/getById/{movieId}")
    public List<ReviewResponse> getAllMovieById(@PathVariable int movieId) {
        return reviewService.getAllMovieById(movieId);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping("/createReview")
    public void CreateReview(@RequestParam int movieId,
                             @RequestParam int userId,
                             @RequestBody ReviewCreationRequest request) {

        reviewService.createReview(movieId,userId,request);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @DeleteMapping("/deleteReview/{reviewId}")
    public void DeleteReview(@PathVariable int reviewId){
        reviewService.deleteReview(reviewId);
    }


    @PreAuthorize("hasAnyAuthority('USER')")
    @PutMapping("/updateReview/{reviewId}")
    public void UpdateReview(@PathVariable int reviewId,@RequestBody ReviewCreationRequest request){
        reviewService.updateReview(reviewId,request);
    }
}
