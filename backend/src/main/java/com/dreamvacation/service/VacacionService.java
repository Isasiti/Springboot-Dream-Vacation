package com.dreamvacation.service;

import com.dreamvacation.model.Usuario;
import com.dreamvacation.model.Vacacion;
import com.dreamvacation.repository.VacacionRepository;
import com.dreamvacation.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacacionService {

    @Autowired
    private VacacionRepository vacacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CountryInfoService countryInfoService;

    /**
     * Obtiene todas las vacaciones de un usuario
     * @param usuarioId ID del usuario
     * @return Lista de vacaciones del usuario
     */
    public List<Vacacion> obtenerVacacionesPorUsuario(Long usuarioId) {
        return vacacionRepository.findByUsuarioId(usuarioId);
    }

    /**
     * Crea una nueva vacación para un usuario
     * @param usuarioId ID del usuario
     * @param vacacion Datos de la vacación
     * @return Vacación creada
     */
    public Vacacion crearVacacion(Long usuarioId, Vacacion vacacion) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        
        CountryInfoService.CountryData countryData = countryInfoService.obtenerDatosPais(vacacion.getPais());
        vacacion.setCiudad(countryData.capital());
        vacacion.setPoblacionActual(countryData.poblacion());
        vacacion.setUsuario(usuario.get());
        return vacacionRepository.save(vacacion);
    }

    /**
     * Obtiene una vacación por su ID
     * @param vacacionId ID de la vacación
     * @return Vacación encontrada
     */
    public Optional<Vacacion> obtenerVacacionPorId(Long vacacionId) {
        return vacacionRepository.findById(vacacionId);
    }

    /**
     * Actualiza una vacación existente
     * @param vacacionId ID de la vacación
     * @param vacacionActualizada Datos actualizados
     * @return Vacación actualizada
     */
    public Vacacion actualizarVacacion(Long vacacionId, Vacacion vacacionActualizada) {
        Optional<Vacacion> vacacion = vacacionRepository.findById(vacacionId);
        
        if (vacacion.isEmpty()) {
            throw new IllegalArgumentException("Vacación no encontrada");
        }
        
        Vacacion v = vacacion.get();
        v.setPais(vacacionActualizada.getPais());
        CountryInfoService.CountryData countryData = countryInfoService.obtenerDatosPais(vacacionActualizada.getPais());
        v.setCiudad(countryData.capital());
        v.setPoblacionActual(countryData.poblacion());
        v.setLugarTuristico(vacacionActualizada.getLugarTuristico());
        v.setDescripcion(vacacionActualizada.getDescripcion());
        
        return vacacionRepository.save(v);
    }

    /**
     * Elimina una vacación
     * @param vacacionId ID de la vacación
     */
    public void eliminarVacacion(Long vacacionId) {
        vacacionRepository.deleteById(vacacionId);
    }
}
