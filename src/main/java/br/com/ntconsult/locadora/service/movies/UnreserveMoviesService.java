package br.com.ntconsult.locadora.service.movies;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.builder.BaseDtoErrorBuilder;
import br.com.ntconsult.locadora.builder.BaseDtoSuccessBuilder;
import br.com.ntconsult.locadora.dto.UnreserveMovieDto;
import br.com.ntconsult.locadora.model.MoviesModel;
import br.com.ntconsult.locadora.repository.MoviesRepository;
import br.com.ntconsult.locadora.validator.UnreserveMovieValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnreserveMoviesService {

    final MoviesRepository moviesRepository;

    public UnreserveMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public BaseDto execute(UnreserveMovieDto dto) {
        List<ErrorDto> errors = UnreserveMovieValidator.execute(dto);
        Optional<MoviesModel> modelOptional = moviesRepository.findByNameIgnoreCase(dto.getName());

        if(!errors.isEmpty()) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.BAD_REQUEST);
            builder.addErrors(errors);
            return builder.get();
        }
        if(!moviesRepository.existsByNameIgnoreCase(dto.getName())) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.NOT_FOUND);
            builder.addError("name", "Filme não encontrado no cadastro.");
            return builder.get();
        }
        if(!modelOptional.get().getMovieReserved()) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "O filme não foi reservado.");
            return builder.get();
        }
        var moviesModel = UnreserveMovieDto.transform(modelOptional);
        moviesRepository.save(moviesModel);
        return new BaseDtoSuccessBuilder<>("Reserva retirada com sucesso.", HttpStatus.OK).get();
    }
}
