package com.dreamvacation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "vacaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String pais;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(name = "poblacion_actual")
    private Long poblacionActual;

    @Column(nullable = false, length = 150)
    private String lugarTuristico;

    @Column(length = 255)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Usuario usuario;

    public Vacacion(String pais, String ciudad, String lugarTuristico) {
        this.pais = pais;
        this.ciudad = ciudad;
        this.lugarTuristico = lugarTuristico;
    }
}
