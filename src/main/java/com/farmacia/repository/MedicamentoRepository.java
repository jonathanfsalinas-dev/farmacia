package com.farmacia.repository;
//esta interfaz representa el repositorio para la entidad "Medicamento",

import com.farmacia.entity.Medicamento;
import com.farmacia.enums.TipoMedicamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// extiende JpaRepository para heredar métodos CRUD básicos
// el primer parámetro es la entidad, el segundo es el tipo de dato de su id
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    // metodo para verificar si existe 
    // un medicamento con el mismo nombre, tipo y marca
    boolean existsByNombreAndTipoAndMarca(
        String nombre,
        TipoMedicamento tipo,
        String marca
    );
}