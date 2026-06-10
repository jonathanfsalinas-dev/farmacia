package com.farmacia.controller;
// Esta clase representa el controlador REST para manejar las solicitudes
//  relacionadas con los movimientos de stock en la farmacia

import com.farmacia.entity.Movimiento;
import com.farmacia.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    //inyección de dependencia del servicio de movimientos
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    // Endpoint para registrar una entrada de stock, recibe el ID del medicamento y la cantidad a ingresar
    @PostMapping("/entrada")
    // ResponseEntity envuelve la respuesta HTTP completa (cuerpo, estado, encabezados)
    //que devuelve el controller al cliente
    public ResponseEntity<Movimiento> registrarEntrada(
            // @RequestParam indica que los parámetros medicamentoId y cantidad
            //  se esperan como parte de la consulta en la URL o
            //  en el cuerpo de la solicitud
            @RequestParam Long medicamentoId,
            @RequestParam Integer cantidad
    ) {
        return ResponseEntity.ok(
                movimientoService.registrarEntrada(medicamentoId, cantidad)
        );
    }

    // Endpoint para registrar una salida de stock, recibe el ID del medicamento y la cantidad a sacar
    @PostMapping("/salida")
    public ResponseEntity<Movimiento> registrarSalida(
            @RequestParam Long medicamentoId,
            @RequestParam Integer cantidad
    ) {
        return ResponseEntity.ok(
                movimientoService.registrarSalida(medicamentoId, cantidad)
        );
    }

    // SOLO ADMIN puede eliminar movimientos, recibe el ID del movimiento a eliminar
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarMovimiento(@PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.ok("Movimiento eliminado correctamente");
    }
}