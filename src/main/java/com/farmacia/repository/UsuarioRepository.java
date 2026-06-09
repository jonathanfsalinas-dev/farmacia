//
package com.farmacia.repository;

import com.farmacia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // metodo para buscar un usuario por su username
    /*OPTIONAL: es una clase de java que se utiliza para representar 
    un valor que puede estar presente o ausente
     */
    Optional<Usuario> findByUsername(String username);

    // metodo para verificar si existe un usuario con el mismo username
    boolean existsByUsername(String username);

}
