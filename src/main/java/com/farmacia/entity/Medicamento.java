package com.farmacia.entity;
//esta clase representa la entidad Medicamento
//  que se mapea a la tabla "medicamentos" en la base de datos

import com.farmacia.enums.TipoMedicamento;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicamento {

    //id, clave primaria
    //generatedValue con estrategia IDENTITY para que se auto-incremente en la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    //tipo de medicamento, solo puede ser de los valores definidos en el enum TipoMedicamento
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMedicamento tipo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private Integer stock;

}