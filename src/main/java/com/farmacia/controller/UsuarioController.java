package com.farmacia.controller;

import com.farmacia.entity.Usuario;
import com.farmacia.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@Tag(name = "Usuarios", description = "Gestión de usuarios en el sistema")
//todas las rutas empiezan con /usuarios
@RequestMapping("/usuarios")
public class UsuarioController {
    //inyeccion dependencias
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping
    @Operation(summary = "Crear un nuevo usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }
    @GetMapping
    @Operation(summary = "Listar todos los usuarios")   
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }   
    @GetMapping("/{id}")
    @Operation(summary = "Buscar un usuario por su ID")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }
    //lo uso para eliminacion "logica" 
    @PutMapping("/{id}/eliminar")
    @Operation(summary = "Realiza una eliminación lógica de un usuario")
    public void eliminarUsuario(@PathVariable Long id) {
    usuarioService.eliminarUsuario(id);
    }
}