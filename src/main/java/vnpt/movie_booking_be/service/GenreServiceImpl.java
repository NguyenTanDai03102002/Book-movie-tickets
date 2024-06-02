package vnpt.movie_booking_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.dto.request.GenreCreationRequest;
import vnpt.movie_booking_be.dto.response.GenreResponse;
import vnpt.movie_booking_be.mapper.GenreMapper;
import vnpt.movie_booking_be.models.Genre;
import vnpt.movie_booking_be.repository.GenreRepository;
import vnpt.movie_booking_be.repository.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreMapper genreMapper;

    @Override
    public List<GenreResponse> getAll() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(genre -> genreMapper.toGenreResponse(genre)).collect(Collectors.toList());
    }

    @Override
    public Page<GenreResponse> getAllGenrePageable(Pageable pageable, String keyword) {
        Page<Genre> genres = genreRepository.findByKeyword(keyword,pageable);
        List<GenreResponse> genreResponses = genres.stream().map(genre -> genreMapper.toGenreResponse(genre)).collect(Collectors.toList());
        return new PageImpl<>(genreResponses, pageable, genres.getTotalElements());
    }

    @Override
    public void createGenre(GenreCreationRequest request) {
        Genre genre = genreRepository.findByName(request.getName());
        if (genre == null) {
            genre = genreMapper.toGenre(request);
            genreRepository.save(genre);
        }else{
            throw new RuntimeException("Genre already exists");
        }
    }

    @Override
    public void deleteGenre(int genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new RuntimeException("Genre not found"));
        if(!genre.getMovies().isEmpty()){
            throw new RuntimeException("Genre was used");
        }else{
            genreRepository.delete(genre);
        }
    }

    @Override
    public void updateGenre(int genreId, GenreCreationRequest request) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new RuntimeException("Genre not found"));
        genreMapper.updateGenre(genre,request);
        genreRepository.save(genre);
    }



}
