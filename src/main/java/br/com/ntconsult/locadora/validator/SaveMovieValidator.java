package br.com.ntconsult.locadora.validator;

import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.dto.SaveMovieDto;

import java.util.ArrayList;
import java.util.List;

public class SaveMovieValidator {

    public static List<ErrorDto> execute(SaveMovieDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if(dto.getName().isBlank()) {
            errors.add(new ErrorDto("name", "Valor obrigatório"));
        }
        if(dto.getTotalQuantity() == null) {
            errors.add(new ErrorDto("totalQuantity", "Valor obrigatório"));
        }
        if(dto.getTotalQuantity() != null && dto.getTotalQuantity() <= 0) {
            errors.add(new ErrorDto("totalQuantity", "Valor deve ser maior que zero"));
        }
        return errors;
    }
}
