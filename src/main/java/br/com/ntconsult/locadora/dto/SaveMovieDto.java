package br.com.ntconsult.locadora.dto;

import br.com.ntconsult.locadora.model.MoviesModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class SaveMovieDto {

    private String name;
    private Integer totalQuantity;

    public SaveMovieDto() {
    }

    public SaveMovieDto(String name, Integer totalQuantity) {
        this.name = name;
        this.totalQuantity = totalQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public static MoviesModel transform(SaveMovieDto dto) {
        MoviesModel model = new MoviesModel();
        BeanUtils.copyProperties(dto, model);
        model.setRentedQuantity(0);
        model.setAvailableQuantity(dto.getTotalQuantity());
        model.setDateFree(LocalDate.now());
        model.setMovieReserved(false);
        model.setTotalDaysReserved(0);
        model.setRentedQuantity(0);
        return model;
    }

}
