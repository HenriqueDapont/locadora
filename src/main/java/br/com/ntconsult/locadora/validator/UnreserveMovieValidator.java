package br.com.ntconsult.locadora.validator;

import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.dto.UnreserveMovieDto;

import java.util.ArrayList;
import java.util.List;

public class UnreserveMovieValidator {

    public static List<ErrorDto> execute(UnreserveMovieDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if(dto.getName().isBlank()) {
            errors.add(new ErrorDto("name", "Valor obrigatório"));
        }
        return errors;
    }
}
