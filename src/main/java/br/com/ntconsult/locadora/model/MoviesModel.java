package br.com.ntconsult.locadora.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "FILMES")
public class MoviesModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer rentedQuantity;
    @Column
    private Integer availableQuantity;
    @Column
    private Integer totalQuantity;
    @Column
    private Integer totalDaysReserved;
    @Column
    private LocalDate rentDate;
    @Column
    private LocalDate devolveDate;
    @Column
    private LocalDate reserveDate;
    @Column
    private LocalDate dateFree;
    @Column
    private Boolean movieReserved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRentedQuantity() {
        return rentedQuantity;
    }

    public void setRentedQuantity(int rentedQuantity) {
        this.rentedQuantity = rentedQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalDaysReserved() {
        return totalDaysReserved;
    }

    public void setTotalDaysReserved(int totalDaysReserved) {
        this.totalDaysReserved = totalDaysReserved;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getDevolveDate() {
        return devolveDate;
    }

    public void setDevolveDate(LocalDate devolveDate) {
        this.devolveDate = devolveDate;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    public LocalDate getDateFree() {
        return dateFree;
    }

    public void setDateFree(LocalDate dateFree) {
        this.dateFree = dateFree;
    }

    public boolean getMovieReserved() {
        return movieReserved;
    }

    public void setMovieReserved(boolean movieReserved) {
        this.movieReserved = movieReserved;
    }
}
