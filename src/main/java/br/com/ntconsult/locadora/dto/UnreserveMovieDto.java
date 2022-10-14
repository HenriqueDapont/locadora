package br.com.ntconsult.locadora.dto;

import br.com.ntconsult.locadora.model.MoviesModel;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class UnreserveMovieDto {

    private String name;

    public UnreserveMovieDto() {
    }

    public UnreserveMovieDto(String name) {
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
        model.setMovieReserved(false);
        model.setReserveDate(null);
        model.setTotalDaysReserved(0);
        model.setAvailableQuantity(model.getAvailableQuantity() + 1);
        return model;
    }
}
