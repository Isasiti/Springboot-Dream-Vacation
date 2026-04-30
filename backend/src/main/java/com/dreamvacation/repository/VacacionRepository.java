package com.dreamvacation.repository;

import com.dreamvacation.model.Vacacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacacionRepository extends JpaRepository<Vacacion, Long> {
    List<Vacacion> findByUsuarioId(Long usuarioId);
}
