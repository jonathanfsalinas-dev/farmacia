package com.farmacia.service;
//esta clase representa el servicio para la entidad "Medicamento",
//  con los métodos para crear, listar, buscar y eliminar medicamentos

import com.farmacia.entity.Medicamento;
import com.farmacia.enums.TipoMedicamento;
import com.farmacia.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService {
    // inyección de dependencia del repositorio
    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    // método para crear un nuevo medicamento, con validación para evitar duplicados
    public Medicamento crear(Medicamento med) {

        boolean existe = medicamentoRepository.existsByNombreAndTipoAndMarca(
                med.getNombre(),
                med.getTipo(),
                med.getMarca()
        );

        if (existe) {
            throw new RuntimeException("Medicamento duplicado");
        }

        return medicamentoRepository.save(med);
    }

    // LISTAR
    public List<Medicamento> listar() {
        return medicamentoRepository.findAll();
    }

    // BUSCAR POR ID
    public Medicamento buscarPorId(Long id) {
        return medicamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
    }

    // ACTUALIZAR STOCK
    public Medicamento actualizarStock(Long id, Integer stock) {

        Medicamento med = buscarPorId(id);

            med.setStock(stock);

        return medicamentoRepository.save(med);
    }

    // ELIMINAR con regla de negocio
    public void eliminar(Long id) {
        // primero buscamos el medicamento por su id
        Medicamento med = buscarPorId(id);

        if (med.getStock() > 0) {
            throw new RuntimeException("No se puede eliminar: stock mayor a 0");
        }

        medicamentoRepository.delete(med);
    }
}