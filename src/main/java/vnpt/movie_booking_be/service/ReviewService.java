package vnpt.movie_booking_be.service;

import vnpt.movie_booking_be.dto.request.ReviewCreationRequest;
import vnpt.movie_booking_be.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    void createReview(int movieId, int userId, ReviewCreationRequest request);

    List<ReviewResponse> getAllMovieById(int movieId);

    void deleteReview(int reviewId);

    void updateReview(int reviewId, ReviewCreationRequest request);
}
