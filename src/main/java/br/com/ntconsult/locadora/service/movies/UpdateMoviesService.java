package br.com.ntconsult.locadora.service.movies;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.builder.BaseDtoErrorBuilder;
import br.com.ntconsult.locadora.builder.BaseDtoSuccessBuilder;
import br.com.ntconsult.locadora.dto.UpdateMovieDto;
import br.com.ntconsult.locadora.repository.MoviesRepository;
import br.com.ntconsult.locadora.validator.UpdateMovieValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateMoviesService {

    final MoviesRepository moviesRepository;

    public UpdateMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public BaseDto execute(UpdateMovieDto dto) {
        List<ErrorDto> errors = UpdateMovieValidator.execute(dto);

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
        var moviesModel = UpdateMovieDto.transform(dto);
        moviesModel.setId(moviesRepository.findByName(dto.getName()).get().getId());
        moviesRepository.save(moviesModel);
        return new BaseDtoSuccessBuilder<>("Dados do filme atualizados com sucesso", HttpStatus.OK).get();
    }
}
