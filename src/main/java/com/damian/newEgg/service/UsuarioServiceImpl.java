package com.damian.newEgg.service;

import com.damian.newEgg.entity.Rol;
import com.damian.newEgg.entity.Usuario;

import com.damian.newEgg.repositorio.RolRepositorio;
import com.damian.newEgg.repositorio.UsuarioRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl  implements UsuarioService, UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder ;
    @Autowired
    private UsuarioRepositorio repositorio;
    @Autowired
    private RolRepositorio rolRepositorio;
    Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Override
    public List<Usuario> findAll() {

        return repositorio.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {

        return repositorio.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        List<Rol>roles = new ArrayList<>();
    Rol rol = new Rol();
    rol.setId(1l);
       Optional<Rol> r = rolRepositorio.findById(rol.getId());
       if (r.isPresent()){
           roles.add(r.get());
       }
        usuario.setEnabled(true);
        usuario.setRoles(roles);
    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    logger.info(usuario.toString());
        return repositorio.save(usuario);
    }

    @Override
    public Usuario findBYUsername(String username) {

        return repositorio.findByUsername(username);
    }

    @Override
    public void delete(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public Boolean exiteUsername(String username) {
        return repositorio.existsByUsername(username);
    }

    @Override
    public Boolean existeEmail(String email) {
        return repositorio.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repositorio.findByUsername(username);
        if(usuario== null){
            logger.error("Usuario no exite");
            throw new UsernameNotFoundException("El usuario no exite");
        }
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role->new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority->logger.info("Role: "+ authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(),usuario.getEnabled(),true,true,true,authorities);
    }
}
