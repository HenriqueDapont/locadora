package br.com.ntconsult.locadora.repository;

import br.com.ntconsult.locadora.model.MoviesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<MoviesModel, Long> {

    boolean existsByNameIgnoreCase(String name);

    Optional<MoviesModel> findByName(String name);

    Optional<MoviesModel> findByNameIgnoreCase(String name);
}
