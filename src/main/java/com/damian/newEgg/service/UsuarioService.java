package com.damian.newEgg.service;

import com.damian.newEgg.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public List<Usuario> findAll();
    public Optional<Usuario>findById(Long id);
    public Usuario save (Usuario usuario);
    public Usuario findBYUsername(String username);
    public void delete (Long id);

}
