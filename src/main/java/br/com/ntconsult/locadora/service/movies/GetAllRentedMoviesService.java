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
public class GetAllRentedMoviesService {

    final MoviesRepository moviesRepository;

    public GetAllRentedMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public BaseDto execute() {
        List<MoviesModel> modelOptional = moviesRepository.findAll();
        List<MoviesModel> rentedMovies = new ArrayList<>();
        for(MoviesModel model : modelOptional) {
            if(model.getRentedQuantity() > 0) {
                rentedMovies.add(model);
            }
        }
        if(rentedMovies.isEmpty()) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError(null, "Nenhum filme está alugado");
            return builder.get();
        }
        return new BaseDtoSuccessBuilder<>(rentedMovies.stream().toList(), HttpStatus.OK).get();
    }
}
