package com.damian.newEgg.service;

import com.damian.newEgg.entity.Noticia;
import com.damian.newEgg.repositorio.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NoticiaServiceImp implements  NoticiaService{
    @Autowired
    private NoticiaRepositorio repositorio;
    @Override
    public List<Noticia> listar() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Noticia> ver(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Noticia save(Noticia noticia) {
        return repositorio.save(noticia);
    }

    @Override
    public void delete(Long id) {
    repositorio.deleteById(id);
    }
}
