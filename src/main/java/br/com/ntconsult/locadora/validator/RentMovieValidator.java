package br.com.ntconsult.locadora.validator;

import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.dto.RentMovieDto;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentMovieValidator {

    public static List<ErrorDto> execute(RentMovieDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if(dto.getName().isBlank()) {
            errors.add(new ErrorDto("name", "Valor obrigatório"));
        }
        if(dto.getDevolveDate() == null) {
            errors.add(new ErrorDto("devolveDate", "Data obrigatória"));
        }
        if(dto.getDevolveDate().isBefore(LocalDate.now()) || dto.getDevolveDate().isEqual(LocalDate.now())) {
            errors.add(new ErrorDto("devolveDate", "Data de devolução precisa ser a partir de amanhã"));
        }
        return errors;
    }
}
