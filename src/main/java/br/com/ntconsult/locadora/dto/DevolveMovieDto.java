package br.com.ntconsult.locadora.dto;

import br.com.ntconsult.locadora.model.MoviesModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Optional;

public class DevolveMovieDto {

    private String name;

    public DevolveMovieDto() {
    }

    public DevolveMovieDto(String name) {
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
        model.setAvailableQuantity(model.getAvailableQuantity() + 1);
        model.setRentedQuantity(model.getRentedQuantity() - 1);
        model.setDevolveDate(LocalDate.now());
        model.setDateFree(LocalDate.now().plusDays(1));
        return model;
    }
}

