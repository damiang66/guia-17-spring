package com.damian.newEgg.service;

import com.damian.newEgg.entity.Noticia;

import java.util.List;
import java.util.Optional;

public interface NoticiaService {
    public List<Noticia> listar();
    public Optional<Noticia>ver(Long id);
    public Noticia save(Noticia noticia);
    public void delete (Long id);
}
