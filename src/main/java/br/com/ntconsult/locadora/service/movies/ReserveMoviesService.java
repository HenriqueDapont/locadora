package br.com.ntconsult.locadora.service.movies;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.builder.BaseDtoErrorBuilder;
import br.com.ntconsult.locadora.builder.BaseDtoSuccessBuilder;
import br.com.ntconsult.locadora.dto.ReserveMovieDto;
import br.com.ntconsult.locadora.model.MoviesModel;
import br.com.ntconsult.locadora.repository.MoviesRepository;
import br.com.ntconsult.locadora.validator.ReserveMovieValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReserveMoviesService {

    final MoviesRepository moviesRepository;

    public ReserveMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public BaseDto execute(ReserveMovieDto dto) {
        List<ErrorDto> errors = ReserveMovieValidator.execute(dto);
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
        if(modelOptional.get().getMovieReserved()) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "O filme já está reservado. É permitida somente uma reserva por título.");
            return builder.get();
        }
        if(dto.getReserveDate().isBefore(modelOptional.get().getDateFree()) && modelOptional.get().getAvailableQuantity() <= 0) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "Não há cópias para reservar nessa data.");
            return builder.get();
        }
        var moviesModel = ReserveMovieDto.transform(dto, modelOptional);
        moviesRepository.save(moviesModel);
        return new BaseDtoSuccessBuilder<>("Filme reservado para o dia " + moviesModel.getReserveDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), HttpStatus.OK).get();
        }
    }
