package br.com.ntconsult.locadora.validator;

import br.com.ntconsult.locadora.base.ErrorDto;
import br.com.ntconsult.locadora.dto.UpdateMovieDto;

import java.util.ArrayList;
import java.util.List;

public class UpdateMovieValidator {

    public static List<ErrorDto> execute(UpdateMovieDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if(dto.getName().isBlank()) {
            errors.add(new ErrorDto("name", "Valor obrigatório"));
        }
        if(dto.getRentedQuantity() == null) {
            errors.add(new ErrorDto("rentedQuantity", "Valor obrigatório"));
        }
        if(dto.getRentedQuantity() != null && dto.getRentedQuantity() < 0) {
            errors.add(new ErrorDto("rentedQuantity", "Valor não pode ser negativo"));
        }
        if(dto.getAvailableQuantity() == null) {
            errors.add(new ErrorDto("availableQuantity", "Valor obrigatório"));
        }
        if(dto.getAvailableQuantity() != null && dto.getAvailableQuantity() < 0) {
            errors.add(new ErrorDto("availableQuantity", "Valor não pode ser negativo"));
        }
        if(dto.getTotalQuantity() == null) {
            errors.add(new ErrorDto("totalQuantity", "Valor obrigatório"));
        }
        if(dto.getTotalQuantity() != null && dto.getTotalQuantity() <= 0) {
            errors.add(new ErrorDto("totalQuantity", "Valor deve ser maior que zero"));
        }
        if(dto.getTotalQuantity() < dto.getAvailableQuantity() + dto.getRentedQuantity()) {
            errors.add(new ErrorDto("totalQuantity", "Valor total não pode ser menor que a soma dos disponíveis e alugados"));
        }
        if(dto.getTotalDaysReserved() == null) {
            errors.add(new ErrorDto("totalDaysReserved", "Valor obrigatório"));
        }
        if(dto.getTotalDaysReserved() != null && dto.getTotalDaysReserved() < 0) {
            errors.add(new ErrorDto("totalQuantity", "Valor não pode ser negativo"));
        }
        if(dto.getMovieReserved() == null) {
            errors.add(new ErrorDto("movieReserved", "Valor obrigatório"));
        }
        return errors;
    }
}
