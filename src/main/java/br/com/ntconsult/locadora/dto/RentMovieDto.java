package br.com.ntconsult.locadora.dto;

import br.com.ntconsult.locadora.model.MoviesModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Optional;

public class RentMovieDto {

    private String name;
    private LocalDate devolveDate;

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

    public LocalDate getDevolveDate() {
        return devolveDate;
    }

    public void setDevolveDate(LocalDate devolveDate) {
        this.devolveDate = devolveDate;
    }

    public static MoviesModel transform(Optional<MoviesModel> modelOptional, RentMovieDto dto) {
        MoviesModel model = new MoviesModel();
        BeanUtils.copyProperties(modelOptional.get(), model);
        if(modelOptional.get().getReserveDate() != null && modelOptional.get().getReserveDate().isBefore(dto.getDevolveDate()) && modelOptional.get().getAvailableQuantity() <= 1) {
            model.setDevolveDate(model.getReserveDate().minusDays(1));
        } else {
            model.setDevolveDate(dto.getDevolveDate());
            model.setDateFree(dto.getDevolveDate().plusDays(1));
        }
        model.setRentDate(LocalDate.now());
        model.setAvailableQuantity(model.getAvailableQuantity() - 1);
        model.setRentedQuantity(model.getRentedQuantity() + 1);
        return model;
    }
}
