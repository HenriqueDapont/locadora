package br.com.ntconsult.locadora.service.movies;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.builder.BaseDtoErrorBuilder;
import br.com.ntconsult.locadora.builder.BaseDtoSuccessBuilder;
import br.com.ntconsult.locadora.dto.RentMovieDto;
import br.com.ntconsult.locadora.model.MoviesModel;
import br.com.ntconsult.locadora.repository.MoviesRepository;
import br.com.ntconsult.locadora.validator.RentMovieValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class RentMoviesService {

    final MoviesRepository moviesRepository;

    public RentMoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public BaseDto execute(RentMovieDto dto) {
        List<ErrorDto> errors = RentMovieValidator.execute(dto);
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
        if(modelOptional.get().getAvailableQuantity() == 0) {
            BaseDtoErrorBuilder builder = new BaseDtoErrorBuilder(HttpStatus.CONFLICT);
            builder.addError(null, "Todas as cópias do filme estão alugadas");
            return builder.get();
        }
        var moviesModel = RentMovieDto.transform(modelOptional, dto);
        moviesRepository.save(moviesModel);
        return new BaseDtoSuccessBuilder<>("Filme alugado com sucesso até " + moviesModel.getDevolveDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), HttpStatus.OK).get();
    }
}
