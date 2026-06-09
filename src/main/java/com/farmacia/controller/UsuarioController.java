package com.farmacia.controller;

import com.farmacia.entity.Usuario;
import com.farmacia.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//todas las rutas empiezan con /usuarios
@RequestMapping("/usuarios")
public class UsuarioController {
    //inyeccion dependencias
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }   
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }
    //lo uso para eliminacion "logica" 
    @PutMapping("/{id}/eliminar")
    public void eliminarUsuario(@PathVariable Long id) {
    usuarioService.eliminarUsuario(id);
    }
}