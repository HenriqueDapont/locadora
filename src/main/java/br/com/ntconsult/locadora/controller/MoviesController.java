package br.com.ntconsult.locadora.controller;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.dto.*;
import br.com.ntconsult.locadora.service.movies.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locadora")
public class MoviesController {

    final SaveMoviesService saveMoviesService;
    final GetAllMoviesService getAllMoviesService;
    final GetOneMoviesService getOneMoviesService;
    final DeleteMoviesService deleteMoviesService;
    final UpdateMoviesService updateMoviesService;
    final RentMoviesService rentMoviesService;
    final DevolveMoviesService devolveMoviesService;
    final GetAllRentedMoviesService getAllRentedMoviesService;
    final GetAllAvailableMoviesService getAllAvailableMoviesService;
    final ReserveMoviesService reserveMoviesService;
    final GetAllReservedMoviesService getAllReservedMoviesService;
    final UnreserveMoviesService unreserveMoviesService;

    public MoviesController(SaveMoviesService saveMoviesService, GetAllMoviesService getAllMoviesService, GetOneMoviesService getOneMoviesService,
                            DeleteMoviesService deleteMoviesService, UpdateMoviesService updateMoviesService, RentMoviesService rentMoviesService,
                            DevolveMoviesService devolveMoviesService, GetAllRentedMoviesService getAllRentedMoviesService,
                            GetAllAvailableMoviesService getAllAvailableMoviesService, ReserveMoviesService reserveMoviesService,
                            GetAllReservedMoviesService getAllReservedMoviesService, UnreserveMoviesService unreserveMoviesService) {
        this.saveMoviesService = saveMoviesService;
        this.getAllMoviesService = getAllMoviesService;
        this.getOneMoviesService = getOneMoviesService;
        this.deleteMoviesService = deleteMoviesService;
        this.updateMoviesService = updateMoviesService;
        this.rentMoviesService = rentMoviesService;
        this.devolveMoviesService = devolveMoviesService;
        this.getAllRentedMoviesService = getAllRentedMoviesService;
        this.getAllAvailableMoviesService = getAllAvailableMoviesService;
        this.reserveMoviesService = reserveMoviesService;
        this.getAllReservedMoviesService = getAllReservedMoviesService;
        this.unreserveMoviesService = unreserveMoviesService;
    }

    @PostMapping
    public BaseDto saveMovie(@RequestBody SaveMovieDto dto) {
        return saveMoviesService.execute(dto);
    }

    @GetMapping("/todos")
    public BaseDto getAllMovies() {
        return getAllMoviesService.execute();
    }

    @GetMapping("/buscar")
    public BaseDto getOneMovie(@RequestBody GetMovieDto dto) {
        return getOneMoviesService.execute(dto);
    }

    @GetMapping("/alugados")
    public BaseDto getAllRentedMovies() {
        return getAllRentedMoviesService.execute();
    }

    @GetMapping("/disponiveis")
    public BaseDto getAllAvailableMovies() {
        return getAllAvailableMoviesService.execute();
    }

    @GetMapping("/reservados")
    public BaseDto getAllReservedMovies() {
        return getAllReservedMoviesService.execute();
    }

    @DeleteMapping("/deletar")
    public BaseDto deleteMovie(@RequestBody DeleteMovieDto dto) {
        return deleteMoviesService.execute(dto);
    }

    @PutMapping("/atualizar")
    public BaseDto updateMovie(@RequestBody UpdateMovieDto dto) {
        return updateMoviesService.execute(dto);
    }

    @PutMapping("/alugar")
    public BaseDto rentMovie(@RequestBody RentMovieDto dto) {
        return rentMoviesService.execute(dto);
    }

    @PutMapping("/devolver")
    public BaseDto returnMovie(@RequestBody DevolveMovieDto dto) {
        return devolveMoviesService.execute(dto);
    }

    @PutMapping("/reservar")
    public BaseDto reserveMovie(@RequestBody ReserveMovieDto dto) {
        return reserveMoviesService.execute(dto);
    }

    @PutMapping("/cancelar")
    public BaseDto unreserveMovie(@RequestBody UnreserveMovieDto dto) {
        return unreserveMoviesService.execute(dto);
    }

}
