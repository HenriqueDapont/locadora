package br.com.ntconsult.locadora.validator;

import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.dto.ReserveMovieDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReserveMovieValidator {

    public static List<ErrorDto> execute(ReserveMovieDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if(dto.getName().isBlank()) {
            errors.add(new ErrorDto("name", "Valor obrigatório"));
        }
        if(dto.getTotalDaysReserved() == null) {
            errors.add(new ErrorDto("totalDaysReserved", "Valor obrigatório"));
        }
        if(dto.getTotalDaysReserved() != null && dto.getTotalDaysReserved() <= 0) {
            errors.add(new ErrorDto("totalDaysReserved", "Valor deve ser maior que zero"));
        }
        if(dto.getReserveDate() == null) {
            errors.add(new ErrorDto("reserveDate", "Data obrigatória"));
        }
        if(dto.getReserveDate() != null && dto.getReserveDate().isBefore(LocalDate.now())) {
            errors.add(new ErrorDto("reserveDate", "A data precisa ser a partir de hoje"));
        }
        return errors;
    }
}
