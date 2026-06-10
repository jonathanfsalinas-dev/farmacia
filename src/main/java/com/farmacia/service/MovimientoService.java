package com.farmacia.service;
// Esta clase representa el servicio para gestionar los movimientos de stock en la farmacia,
//  con métodos para registrar entradas, salidas y eliminar movimientos, además de obtener el usuario

import com.farmacia.entity.Medicamento;
import com.farmacia.entity.Movimiento;
import com.farmacia.entity.Usuario;
import com.farmacia.enums.TipoMovimiento;
import com.farmacia.repository.MedicamentoRepository;
import com.farmacia.repository.MovimientoRepository;
import com.farmacia.repository.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovimientoService {

    //inyección de dependencias de los repositorios
    private final MovimientoRepository movimientoRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final UsuarioRepository usuarioRepository;

    // Constructor para inyectar los repositorios necesarios en el servicio
    public MovimientoService(
            MovimientoRepository movimientoRepository,
            MedicamentoRepository medicamentoRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.movimientoRepository = movimientoRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //registrar entrada de stock
    //transactional para asegurar que la operación sea atómica, es decir, que se realice completamente o no se realice en caso de error
    @Transactional
    public Movimiento registrarEntrada(Long medicamentoId, Integer cantidad) {

        // buscar el medicamento por su ID, si no se encuentra, lanzar una excepción
        Medicamento medicamento = medicamentoRepository.findById(medicamentoId)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

        
        Usuario usuario = obtenerUsuarioLogueado();

        medicamento.setStock(medicamento.getStock() + cantidad);

        // crear un nuevo movimiento de tipo ENTRADA con la cantidad, medicamento y usuario asociados
        Movimiento movimiento = Movimiento.builder()
            // Hibernate selecciona automáticamente los valores del objeto Movimiento
            // y los mapea a las columnas correspondientes en la tabla movimientos
            // al momento de realizar la operación de persistencia (INSERT)
                .tipo(TipoMovimiento.ENTRADA)
                .cantidad(cantidad)
                .medicamento(medicamento)
                .usuario(usuario)
                .build();

        return movimientoRepository.save(movimiento);
    }

    //registrar salida de stock, con validación para evitar que el stock quede negativo
    @Transactional
    public Movimiento registrarSalida(Long medicamentoId, Integer cantidad) {

        // buscar el medicamento por su ID, si no se encuentra, lanzar una excepción
        Medicamento medicamento = medicamentoRepository.findById(medicamentoId)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

        if (medicamento.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        Usuario usuario = obtenerUsuarioLogueado();

        // actualizar el stock del medicamento restando la cantidad de salida
        medicamento.setStock(medicamento.getStock() - cantidad);

        Movimiento movimiento = Movimiento.builder()
                .tipo(TipoMovimiento.SALIDA)
                .cantidad(cantidad)
                .medicamento(medicamento)
                .usuario(usuario)
                .build();

        return movimientoRepository.save(movimiento);
    }

    // eliminar un movimiento, con lógica para revertir el stock en caso de eliminar una entrada o salida
    @Transactional
    public void eliminarMovimiento(Long id) {

        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

        Medicamento medicamento = movimiento.getMedicamento();

        // revertir stock
        if (movimiento.getTipo() == TipoMovimiento.ENTRADA) {
            //
            medicamento.setStock(medicamento.getStock() - movimiento.getCantidad());
        } else {
            medicamento.setStock(medicamento.getStock() + movimiento.getCantidad());
        }

        movimientoRepository.delete(movimiento);
    }

    // método auxiliar para obtener el usuario actualmente logueado en el sistema
    private Usuario obtenerUsuarioLogueado() {

        // SecurityContextHolder es una clase de Spring Security que permite acceder al contexto de seguridad actual,
        //  donde se almacena la información de autenticación del usuario logueado.
        String username = SecurityContextHolder.getContext()    
                // getAuthentication() devuelve el objeto de autenticación actual, 
                // que contiene detalles sobre el usuario autenticado.  
                .getAuthentication()
                .getName();
        //busca el usuario por username para obtener el objeto Usuario completo
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}