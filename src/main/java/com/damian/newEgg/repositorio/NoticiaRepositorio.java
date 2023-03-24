package com.damian.newEgg.repositorio;

import com.damian.newEgg.entity.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepositorio extends JpaRepository<Noticia,Long> {
}
