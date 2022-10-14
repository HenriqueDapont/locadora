package br.com.ntconsult.locadora.dto;

import br.com.ntconsult.locadora.model.MoviesModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Optional;

public class RentMovieDto {

    private String name;

    public RentMovieDto() {
    }

    public RentMovieDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static MoviesModel transform(Optional<MoviesModel> modelOptional) {
        MoviesModel model = new MoviesModel();
        BeanUtils.copyProperties(modelOptional.get(), model);
        if(modelOptional.get().getReserveDate() != null && modelOptional.get().getReserveDate().isBefore(LocalDate.now().plusDays(3)) && modelOptional.get().getAvailableQuantity() <= 1) {
            model.setDevolveDate(model.getReserveDate().minusDays(1));
        } else {
            model.setDevolveDate(LocalDate.now().plusDays(3));
            model.setDateFree(LocalDate.now().plusDays(4));
        }
        model.setRentDate(LocalDate.now());
        model.setAvailableQuantity(model.getAvailableQuantity() - 1);
        model.setRentedQuantity(model.getRentedQuantity() + 1);
        return model;
    }
}
