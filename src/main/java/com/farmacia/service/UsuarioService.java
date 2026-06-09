package com.farmacia.service;
//esta clase representa el servicio para la entidad "Usuario",
//  con los métodos para crear, listar, buscar y eliminar usuarios,
//  utilizando el repositorio UsuarioRepository para interactuar con la base de datos.  

import com.farmacia.entity.Usuario;
import com.farmacia.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
// crea una instancia creada por spring, patron singleton
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario crearUsuario(Usuario usuario) {
        
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new IllegalArgumentException("El username ya existe");
        }
        // por defecto, el nuevo usuario se crea como activo
        usuario.setActivo(true);
        
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> listarUsuarios() {
    return usuarioRepository.findAll();
    }
    
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }
    
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        usuario.setActivo(false);
        usuario.setBloqueado(true);

        usuarioRepository.save(usuario);
    }
}
