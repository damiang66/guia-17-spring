package com.damian.newEgg.controlador;

import com.damian.newEgg.entity.Noticia;
import com.damian.newEgg.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/noticia")
public class NoticiaControlador {
    @Autowired
    private NoticiaService service;

    private ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e-> {
            errores.put(e.getField(), "El campo: " + e.getField() + " " + e.getDefaultMessage());
        });
        return new ResponseEntity<>(errores, HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<?>listar(){
        return ResponseEntity.ok().body(service.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>verUno(@PathVariable Long id){
        Optional<Noticia> r = service.ver(id);
        if (r.isPresent()){
            Noticia noticia= r.get();
            return ResponseEntity.ok().body(noticia);

        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?>guardar(@Valid @RequestBody Noticia noticia, BindingResult result){
        if (result.hasErrors()){

            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(noticia));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>editar(@Valid @RequestBody Noticia noticia,BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return this.validar(result);
        }
        Optional<Noticia>r = service.ver(id);
        if (r.isPresent()){
            Noticia noticiaDb = r.get();
            noticiaDb.setTitulo(noticia.getTitulo());
            noticiaDb.setCuerpo(noticia.getCuerpo());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(noticiaDb));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Noticia> r = service.ver(id);
        if (r.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>("Noticia no encontrada",HttpStatus.NOT_FOUND);

    }
    @GetMapping("/buscar/{termino}")
    public ResponseEntity<?>buscarPorTitulo(@PathVariable String termino){
        return ResponseEntity.ok().body(service.buscarPorTitulo(termino));
    }


}