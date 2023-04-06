package com.damian.newEgg.repositorio;

import com.damian.newEgg.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    public Usuario findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
