package com.farmacia.controller;
//esta clase representa el controlador para la entidad "Medicamento",
//  con los endpoints para crear, listar, buscar y eliminar medicamentos

import com.farmacia.entity.Medicamento;
import com.farmacia.service.MedicamentoService;
//importaciones necesarias para las anotaciones de Spring y para manejar listas
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@Tag(name = "Medicamentos", description = "Gestión de medicamentos en el sistema")
// mapea todas las rutas de este controlador bajo "/medicamentos"
@RequestMapping("/medicamentos")
public class MedicamentoController {
    // inyección de dependencia del servicio
    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    // CREAR
    @PostMapping
    @Operation(summary = "Crear un nuevo medicamento")
    public Medicamento crear(@RequestBody Medicamento medicamento) {
        return medicamentoService.crear(medicamento);
    }

    // LISTAR
    @GetMapping
    @Operation(summary = "Listar todos los medicamentos")
    public List<Medicamento> listar() {
        return medicamentoService.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar un medicamento por su ID") 
    public Medicamento buscar(@PathVariable Long id) {
        return medicamentoService.buscarPorId(id);
    }

    //actualizo el stock
    @PatchMapping("/{id}/stock")
    @Operation(summary = "Actualizar el stock de un medicamento")
        public Medicamento actualizarStock(@PathVariable Long id,@RequestBody Integer stock) {
        return medicamentoService.actualizarStock(id, stock);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar el stock de un medicamento")
        public Medicamento actualizar(@PathVariable Long id,
                              @RequestBody Medicamento medicamento) {
        return medicamentoService.actualizarStock(id, medicamento.getStock());
    }

    // ELIMINAR (regla stock > 0)
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un medicamento")
    public void eliminar(@PathVariable Long id) {
        medicamentoService.eliminar(id);
    }
}
