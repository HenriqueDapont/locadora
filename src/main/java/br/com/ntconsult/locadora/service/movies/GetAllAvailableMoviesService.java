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
public class GetAllAvailableMoviesService {

    final MoviesRepository moviesRepository;

    public GetAllAvailableMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public BaseDto execute() {
        List<MoviesModel> modelOptional = moviesRepository.findAll();
        List<MoviesModel> availableMovies = new ArrayList<>();
        for (MoviesModel model : modelOptional) {
            if (model.getAvailableQuantity() > 0) {
                availableMovies.add(model);
            }
        }
        if(availableMovies.isEmpty()) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError(null, "Nenhum filme está disponível");
            return builder.get();
        }
        return new BaseDtoSuccessBuilder<>(availableMovies.stream().toList(), HttpStatus.OK).get();
    }
}
