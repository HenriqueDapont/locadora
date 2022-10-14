package br.com.ntconsult.locadora.service.movies;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.builder.BaseDtoErrorBuilder;
import br.com.ntconsult.locadora.builder.BaseDtoSuccessBuilder;
import br.com.ntconsult.locadora.dto.SaveMovieDto;
import br.com.ntconsult.locadora.repository.MoviesRepository;
import br.com.ntconsult.locadora.validator.SaveMovieValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SaveMoviesService {

    final MoviesRepository moviesRepository;

    public SaveMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Transactional
    public BaseDto execute(SaveMovieDto dto) {
        List<ErrorDto> errors = SaveMovieValidator.execute(dto);

        if(!errors.isEmpty()) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.BAD_REQUEST);
            builder.addErrors(errors);
            return builder.get();
        }
        if(moviesRepository.existsByNameIgnoreCase(dto.getName())) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "Um filme com esse nome já está cadastrado.");
            return builder.get();
        }
        var moviesModel = SaveMovieDto.transform(dto);
        moviesRepository.save(moviesModel);
        return new BaseDtoSuccessBuilder<>("Filme inserido com sucesso.", HttpStatus.CREATED).get();
    }

}
