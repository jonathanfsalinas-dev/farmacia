package com.farmacia.security;

// Esta clase se encarga de cargar los detalles del usuario desde la base de datos
// y adaptarlos al formato que Spring Security espera


import com.farmacia.entity.Usuario;
import com.farmacia.repository.UsuarioRepository;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioDetailsService implements UserDetailsService {

    // inyeccion de dependencias del repositorio de usuarios
    private final UsuarioRepository usuarioRepository;
    
    // Constructor que recibe el repositorio de usuarios (inyección de dependencias)
    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // spring security llama a este metodo para cargar los detalles del usuario durante el proceso de autenticación
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // 1. Buscar el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado: " + username)
                );

        // 2. Convertir Usuario → UserDetails (adapter)
        return new UsuarioDetailsAdapter(usuario);
    }
}