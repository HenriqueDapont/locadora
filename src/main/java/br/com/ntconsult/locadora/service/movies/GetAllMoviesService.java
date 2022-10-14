package br.com.ntconsult.locadora.service.movies;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.builder.BaseDtoErrorBuilder;
import br.com.ntconsult.locadora.builder.BaseDtoSuccessBuilder;
import br.com.ntconsult.locadora.model.MoviesModel;
import br.com.ntconsult.locadora.repository.MoviesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllMoviesService {

    final MoviesRepository moviesRepository;

    public GetAllMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public BaseDto execute() {
        List<MoviesModel> modelOptional = moviesRepository.findAll();
        if(modelOptional.isEmpty()) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError(null, "Nenhum filme está cadastrado");
            return builder.get();
        }
        return new BaseDtoSuccessBuilder<>(modelOptional.stream().toList(), HttpStatus.OK).get();
    }
}
