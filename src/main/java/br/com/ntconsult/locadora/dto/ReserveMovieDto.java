package br.com.ntconsult.locadora.dto;

import br.com.ntconsult.locadora.model.MoviesModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Optional;

public class ReserveMovieDto {

    private String name;
    private Integer totalDaysReserved;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate reserveDate;

    public ReserveMovieDto() {
    }

    public ReserveMovieDto(String name, Integer totalDaysReserved, LocalDate reserveDate) {
        this.name = name;
        this.totalDaysReserved = totalDaysReserved;
        this.reserveDate = reserveDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalDaysReserved() {
        return totalDaysReserved;
    }

    public void setTotalDaysReserved(Integer totalDaysReserved) {
        this.totalDaysReserved = totalDaysReserved;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    public static MoviesModel transform(ReserveMovieDto dto, Optional<MoviesModel> modelOptional) {
        MoviesModel model = new MoviesModel();
        BeanUtils.copyProperties(modelOptional.get(), model);
        model.setAvailableQuantity(model.getAvailableQuantity());
        model.setMovieReserved(true);
        model.setTotalDaysReserved(dto.getTotalDaysReserved());
        model.setReserveDate(dto.getReserveDate());
        return model;
    }
}
