package br.com.ntconsult.locadora.service.movies;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.builder.BaseDtoErrorBuilder;
import br.com.ntconsult.locadora.builder.BaseDtoSuccessBuilder;
import br.com.ntconsult.locadora.dto.DevolveMovieDto;
import br.com.ntconsult.locadora.model.MoviesModel;
import br.com.ntconsult.locadora.repository.MoviesRepository;
import br.com.ntconsult.locadora.validator.DevolveMovieValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevolveMoviesService {

    final MoviesRepository moviesRepository;

    public DevolveMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public BaseDto execute(DevolveMovieDto dto) {
        List<ErrorDto> errors = DevolveMovieValidator.execute(dto);
        Optional<MoviesModel> modelOptional = moviesRepository.findByNameIgnoreCase(dto.getName());

        if(!errors.isEmpty()) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.BAD_REQUEST);
            builder.addErrors(errors);
            return builder.get();
        }
        if(!moviesRepository.existsByNameIgnoreCase(dto.getName())) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError("name", "Filme não encontrado no cadastro");
            return builder.get();
        }
        if(modelOptional.get().getRentedQuantity() == 0) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "O filme não tem cópias alugadas");
            return builder.get();
        }
        var moviesModel = DevolveMovieDto.transform(modelOptional);
        moviesRepository.save(moviesModel);
        return new BaseDtoSuccessBuilder<>("Filme devolvido com sucesso", HttpStatus.OK).get();
    }

}
