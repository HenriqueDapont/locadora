package br.com.ntconsult.locadora.service.movies;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.builder.BaseDtoErrorBuilder;
import br.com.ntconsult.locadora.builder.BaseDtoSuccessBuilder;
import br.com.ntconsult.locadora.dto.DeleteMovieDto;
import br.com.ntconsult.locadora.model.MoviesModel;
import br.com.ntconsult.locadora.repository.MoviesRepository;
import br.com.ntconsult.locadora.validator.DeleteMovieValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DeleteMoviesService {

    final MoviesRepository moviesRepository;

    public DeleteMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Transactional
    public BaseDto execute(DeleteMovieDto dto) {
        List<ErrorDto> errors = DeleteMovieValidator.execute(dto);
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
        moviesRepository.delete(modelOptional.get());
        return new BaseDtoSuccessBuilder<>("Filme deletado com sucesso", HttpStatus.OK).get();
    }
}
