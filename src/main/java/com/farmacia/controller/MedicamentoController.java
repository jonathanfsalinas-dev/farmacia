package com.farmacia.controller;
//esta clase representa el controlador para la entidad "Medicamento",
//  con los endpoints para crear, listar, buscar y eliminar medicamentos

import com.farmacia.entity.Medicamento;
import com.farmacia.service.MedicamentoService;
//importaciones necesarias para las anotaciones de Spring y para manejar listas
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public Medicamento crear(@RequestBody Medicamento medicamento) {
        return medicamentoService.crear(medicamento);
    }

    // LISTAR
    @GetMapping
    public List<Medicamento> listar() {
        return medicamentoService.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Medicamento buscar(@PathVariable Long id) {
        return medicamentoService.buscarPorId(id);
    }

    //actualizo el stock
    @PatchMapping("/{id}/stock")
        public Medicamento actualizarStock(@PathVariable Long id,@RequestBody Integer stock) {
        return medicamentoService.actualizarStock(id, stock);
    }
    @PutMapping("/{id}")
        public Medicamento actualizar(@PathVariable Long id,
                              @RequestBody Medicamento medicamento) {
        return medicamentoService.actualizarStock(id, medicamento.getStock());
    }

    // ELIMINAR (regla stock > 0)
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        medicamentoService.eliminar(id);
    }
}
