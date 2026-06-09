package com.farmacia.security;

import com.farmacia.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Esta clase adapta nuestro modelo de Usuario al formato que Spring Security espera
public class UsuarioDetailsAdapter implements UserDetails {

    // Referencia al objeto Usuario original
    private final Usuario usuario;

    // Constructor que recibe un objeto Usuario 
    public UsuarioDetailsAdapter(Usuario usuario) {
        this.usuario = usuario;
    }

    // Devuelve una colección de autoridades (roles) del usuario
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        
        return List.of(
                new SimpleGrantedAuthority(
                        "ROLE_" + usuario.getRol().name()
                )
        );
    }

    // Devuelve la contraseña del usuario
    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    // Devuelve el nombre de usuario
    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    // Indica si la cuenta expiró
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indica si la cuenta está bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indica si las credenciales expiraron
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indica si el usuario está habilitado
    @Override
    public boolean isEnabled() {
        return usuario.getActivo();
    }
}