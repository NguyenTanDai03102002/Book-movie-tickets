package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.request.ReviewCreationRequest;
import vnpt.movie_booking_be.dto.response.ReviewResponse;
import vnpt.movie_booking_be.mapper.ReviewMapper;
import vnpt.movie_booking_be.models.Movie;
import vnpt.movie_booking_be.models.Review;
import vnpt.movie_booking_be.models.User;
import vnpt.movie_booking_be.repository.MovieRepository;
import vnpt.movie_booking_be.repository.ReviewRepository;
import vnpt.movie_booking_be.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewResponse> getAllMovieById(int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("movie not found"));
        List<Review> reviewList = reviewRepository.findByMovie(movie);
        return reviewList.stream().map(review -> reviewMapper.toReviewResponse(review))
                .collect(Collectors.toList());
    }

    @Override
    public void createReview(int movieId, int userId, ReviewCreationRequest request) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("movie not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        reviewRepository.save(Review.builder()
                        .content(request.getContent())
                        .numberStar(request.getNumberStar())
                        .date(new Date())
                        .movie(movie)
                        .user(user)
                .build());

    }

    @Override
    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public void updateReview(int reviewId, ReviewCreationRequest request) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("review not found"));
        review.setContent(request.getContent());
        review.setNumberStar(request.getNumberStar());
        review.setDate( new Date());
        reviewRepository.save(review);
    }


}
