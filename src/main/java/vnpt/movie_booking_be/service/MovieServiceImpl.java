package vnpt.movie_booking_be.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vnpt.movie_booking_be.dto.response.MovieResponse;
import vnpt.movie_booking_be.mapper.MovieMapper;
import vnpt.movie_booking_be.models.Genre;
import vnpt.movie_booking_be.models.Movie;
import vnpt.movie_booking_be.repository.GenreRepository;
import vnpt.movie_booking_be.repository.MovieRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        LocalDate today = LocalDate.now();
        return movies.stream()
<<<<<<< HEAD
             //   .filter(movie -> movie.getRelease_date().isBefore(today) && movie.getEnd_date().isAfter(today))
=======
                .filter(movie -> movie.getRelease_date().isBefore(today) && movie.getEnd_date().isAfter(today))
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
                .map(movie -> movieMapper.movieToMovieResponse(movie))
                .collect(Collectors.toList());
    }

    @Override
    public void createMovie(String title, Set<Integer> genreIds, String description,
                             MultipartFile file, MultipartFile video,
                             String director, String casts, String duration, float rating,
                             String releaseDate, String endDate) {
        Map<String, Object> uploadImg = uploadImage(file);
        String imageUrl = (String) uploadImg.get("url");
        Map<String, Object> uploadVideo = uploadVideo(video);
        String videoUrl = (String) uploadVideo.get("url");

        int durationInt = Integer.parseInt(duration);
        LocalDate releaseDateLocal = LocalDate.parse(releaseDate);

        LocalDate endDateLocal = LocalDate.parse(endDate);

        Set<String> castSet = new HashSet<>();
        for (String cast : casts.split(",")) {
            castSet.add(cast.trim());
        }

        Set<Genre> genres = new HashSet<>();
        for (Integer genreId : genreIds) {
            genreRepository.findById(genreId).ifPresent(genres::add);
        }

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setImage(imageUrl);
        movie.setTrailer(videoUrl);
        movie.setDirector(director);
        movie.setCasts(castSet);
        movie.setDuration(durationInt);
        movie.setRating(rating);
        movie.setRelease_date(releaseDateLocal);
        movie.setEnd_date(endDateLocal);
        movie.setGenres(genres);

        movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Integer id, String title, Set<Integer> genreIds, String description,
                             MultipartFile file, MultipartFile video,
                             String director, String casts, String duration, String rating,
                             String releaseDate, String endDate) {
        MovieResponse mvr = getMovieById(id);
        if(title != null) {
            mvr.setTitle(mvr.getTitle());

        }
        // Lấy thông tin bộ phim cần cập nhật từ cơ sở dữ liệu
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();

            // Upload ảnh mới nếu có
            Map<String, Object> uploadImg = uploadImage(file);
            String imageUrl = (String) uploadImg.get("url");

            // Upload video mới nếu có
            Map<String, Object> uploadVideo = uploadVideo(video);
            String videoUrl = (String) uploadVideo.get("url");

            // Cập nhật thông tin bộ phim với thông tin mới
            movie.setTitle(title);
            movie.setDescription(description);
            movie.setImage(imageUrl);
            movie.setTrailer(videoUrl);
            movie.setDirector(director);
            movie.setCasts(new HashSet<>(Arrays.asList(casts.split(","))));
            movie.setDuration(Integer.parseInt(duration));

            // movie.setRating(Float.parseFloat(rating));
            movie.setRelease_date(LocalDate.parse(releaseDate));
            movie.setEnd_date(LocalDate.parse(endDate));

            // Lấy danh sách thể loại từ danh sách id
            Set<Genre> genres = new HashSet<>();
            for (Integer genreId : genreIds) {
                genreRepository.findById(genreId).ifPresent(genres::add);
            }

            // Xóa các thể loại trùng lặp
            Set<Genre> existingGenres = movie.getGenres();
            genres.removeAll(existingGenres);

            // Thêm các thể loại mới
            existingGenres.addAll(genres);
            movie.setGenres(existingGenres);

            // Lưu và trả về bộ phim đã cập nhật
            return movieRepository.save(movie);
        } else {
            // Nếu không tìm thấy bộ phim, ném ngoại lệ hoặc trả về null tùy theo yêu cầu
            throw new RuntimeException("Movie not found with id: " + id);
        }
    }

    public Map<String, Object> uploadImage(MultipartFile file) {
        try {
            return this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException io) {
            throw new RuntimeException("Image upload failed", io);
        }
    }
    public Map<String, Object> uploadVideo(MultipartFile file) {
        try {
            return this.cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "video"));
        } catch (IOException io) {
            throw new RuntimeException("Video upload failed", io);
        }
    }

    public MovieResponse getMovieById(Integer id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            return movieMapper.movieToMovieResponse(movie);
        } else {
            throw new RuntimeException("Movie not found with id: " + id);
        }
    }
<<<<<<< HEAD

=======
>>>>>>> 5b16deb64ee75b07ddf34a11fcbd5bef5619ff79
}
