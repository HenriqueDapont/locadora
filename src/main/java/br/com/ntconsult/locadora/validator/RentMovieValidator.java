package br.com.ntconsult.locadora.validator;

import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.dto.RentMovieDto;

import java.util.ArrayList;
import java.util.List;

public class RentMovieValidator {

    public static List<ErrorDto> execute(RentMovieDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if(dto.getName().isBlank()) {
            errors.add(new ErrorDto("name", "Valor obrigatório"));
        }
        return errors;
    }
}
