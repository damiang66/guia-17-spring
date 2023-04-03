package com.damian.newEgg.repositorio;

import com.damian.newEgg.entity.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticiaRepositorio extends JpaRepository<Noticia,Long> {
    public List<Noticia> findByTituloContainingIgnoreCase(String term);
}
