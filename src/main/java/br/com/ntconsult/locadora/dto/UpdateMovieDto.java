package br.com.ntconsult.locadora.dto;

import br.com.ntconsult.locadora.model.MoviesModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class UpdateMovieDto {

    private String name;
    private Integer rentedQuantity;
    private Integer availableQuantity;
    private Integer totalQuantity;
    private Integer totalDaysReserved;
    private Boolean movieReserved;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate rentDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate reserveDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate devolveDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFree;

    public UpdateMovieDto() {
    }

    public UpdateMovieDto(String name, Integer rentedQuantity, Integer availableQuantity,
                          Integer totalQuantity, Integer totalDaysReserved, Boolean movieReserved,
                          LocalDate rentDate, LocalDate reserveDate, LocalDate devolveDate, LocalDate dateFree) {
        this.name = name;
        this.rentedQuantity = rentedQuantity;
        this.availableQuantity = availableQuantity;
        this.totalQuantity = totalQuantity;
        this.totalDaysReserved = totalDaysReserved;
        this.movieReserved = movieReserved;
        this.rentDate = rentDate;
        this.reserveDate = reserveDate;
        this.devolveDate = devolveDate;
        this.dateFree = dateFree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRentedQuantity() {
        return rentedQuantity;
    }

    public void setRentedQuantity(Integer rentedQuantity) {
        this.rentedQuantity = rentedQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getTotalDaysReserved() {
        return totalDaysReserved;
    }

    public void setTotalDaysReserved(Integer totalDaysReserved) {
        this.totalDaysReserved = totalDaysReserved;
    }

    public Boolean getMovieReserved() {
        return movieReserved;
    }

    public void setMovieReserved(Boolean movieReserved) {
        this.movieReserved = movieReserved;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    public LocalDate getDevolveDate() {
        return devolveDate;
    }

    public void setDevolveDate(LocalDate devolveDate) {
        this.devolveDate = devolveDate;
    }

    public LocalDate getDateFree() {
        return dateFree;
    }

    public void setDateFree(LocalDate dateFree) {
        this.dateFree = dateFree;
    }

    public static MoviesModel transform(UpdateMovieDto dto) {
        MoviesModel model = new MoviesModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }
}
