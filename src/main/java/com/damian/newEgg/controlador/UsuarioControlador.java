package com.damian.newEgg.controlador;

import com.damian.newEgg.entity.Usuario;
import com.damian.newEgg.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    @Autowired
    private UsuarioService service;
    private ResponseEntity<?>validar(BindingResult result){
        Map<String,Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e->{
            errores.put(e.getField(),"El campo: " + e.getField() + " "+ e.getDefaultMessage());
        });
        return new ResponseEntity<>(errores,HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Usuario> r = service.findById(id);
        if (r.isPresent()){
            return ResponseEntity.ok().body(r.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?>save (@Valid @RequestBody Usuario usuario, BindingResult result){
        if (result.hasErrors()){
            return this.validar(result);
        }
        if (!usuario.getEmail().isEmpty() && service.existeEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "Ya existe! un usuario con ese email electrónico!"));
        }
        if (!usuario.getUsername().isEmpty() && service.exiteUsername(usuario.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "Ya existe! un usuario con ese username!"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>editar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return this.validar(result);
        }
        Optional<Usuario> r = service.findById(id);
        if (r.isPresent()){
            Usuario usuarioDb = r.get();
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setApellido(usuario.getApellido());
            usuarioDb.setEmail(usuario.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        return ResponseEntity.noContent().build();
    }

}
