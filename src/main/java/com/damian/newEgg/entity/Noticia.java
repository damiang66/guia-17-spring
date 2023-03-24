package com.damian.newEgg.entity;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@Table(name = "noticias")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String cuerpo;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @PrePersist
    public void pre(){
        this.fecha= new Date();
    }

}
