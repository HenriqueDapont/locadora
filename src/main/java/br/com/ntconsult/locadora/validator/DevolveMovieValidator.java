package br.com.ntconsult.locadora.validator;

import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.dto.DevolveMovieDto;

import java.util.ArrayList;
import java.util.List;

public class DevolveMovieValidator {

    public static List<ErrorDto> execute(DevolveMovieDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if(dto.getName().isBlank()) {
            errors.add(new ErrorDto("name", "Valor obrigatório"));
        }
        return errors;
    }
}
