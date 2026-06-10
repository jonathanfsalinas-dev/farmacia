package com.farmacia.entity;

// esta clase representa un movimiento de stock en la farmacia

import com.farmacia.enums.TipoMovimiento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Getter
@Setter
// Constructor sin argumentos, necesario para JPA
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    // id, clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Integer cantidad;

    // tipo de movimiento: ENTRADA o SALIDA
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimiento tipo;

    // usuario que realizó el movimiento
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // medicamento asociado al movimiento
    // Relación ManyToOne con Medicamento, 
    // un movimiento está asociado a un medicamento específico
    @ManyToOne(optional = false)
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
    }
}