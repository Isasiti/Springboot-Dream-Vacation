package com.dreamvacation.controller;

import com.dreamvacation.model.Vacacion;
import com.dreamvacation.service.VacacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vacaciones")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VacacionController {

    @Autowired
    private VacacionService vacacionService;

    /**
     * Obtiene todas las vacaciones de un usuario
     * GET /api/vacaciones/usuario/{usuarioId}
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> obtenerVacacionesPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<Vacacion> vacaciones = vacacionService.obtenerVacacionesPorUsuario(usuarioId);
            return ResponseEntity.ok(vacaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener vacaciones: " + e.getMessage());
        }
    }

    /**
     * Obtiene una vacación específica por ID
     * GET /api/vacaciones/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVacacion(@PathVariable Long id) {
        try {
            Optional<Vacacion> vacacion = vacacionService.obtenerVacacionPorId(id);
            
            if (vacacion.isPresent()) {
                return ResponseEntity.ok(vacacion.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vacación no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la vacación: " + e.getMessage());
        }
    }

    /**
     * Crea una nueva vacación para un usuario
     * POST /api/vacaciones/usuario/{usuarioId}
     */
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> crearVacacion(@PathVariable Long usuarioId, @RequestBody Vacacion vacacion) {
        try {
            Vacacion nuevaVacacion = vacacionService.crearVacacion(usuarioId, vacacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVacacion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la vacación: " + e.getMessage());
        }
    }

    /**
     * Actualiza una vacación existente
     * PUT /api/vacaciones/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVacacion(@PathVariable Long id, @RequestBody Vacacion vacacion) {
        try {
            Vacacion vacacionActualizada = vacacionService.actualizarVacacion(id, vacacion);
            return ResponseEntity.ok(vacacionActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la vacación: " + e.getMessage());
        }
    }

    /**
     * Elimina una vacación
     * DELETE /api/vacaciones/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVacacion(@PathVariable Long id) {
        try {
            vacacionService.eliminarVacacion(id);
            return ResponseEntity.ok("Vacación eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la vacación: " + e.getMessage());
        }
    }
}
