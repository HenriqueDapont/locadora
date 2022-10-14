package br.com.ntconsult.locadora.service.movies;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.builder.BaseDtoErrorBuilder;
import br.com.ntconsult.locadora.builder.BaseDtoSuccessBuilder;
import br.com.ntconsult.locadora.model.MoviesModel;
import br.com.ntconsult.locadora.repository.MoviesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllReservedMoviesService {

    final MoviesRepository moviesRepository;

    public GetAllReservedMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public BaseDto execute() {
        List<MoviesModel> modelOptional = moviesRepository.findAll();
        List<MoviesModel> reservedMovies = new ArrayList<>();
        for (MoviesModel model : modelOptional) {
            if (model.getMovieReserved()) {
                reservedMovies.add(model);
            }
        }
        if(reservedMovies.isEmpty()) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError(null, "Nenhum filme está reservado");
            return builder.get();
        }
        return new BaseDtoSuccessBuilder<>(reservedMovies.stream().toList(), HttpStatus.OK).get();
    }
}
