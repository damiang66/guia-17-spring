package com.damian.newEgg.controlador;

import com.damian.newEgg.entity.Noticia;
import com.damian.newEgg.service.FotoService;
import com.damian.newEgg.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/noticia")
public class NoticiaControlador {
    @Autowired
    private NoticiaService service;
    @Autowired
    private FotoService fotoService;

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
            String nombreFotoAnterior = r.get().getFoto();
            fotoService.eliminar(nombreFotoAnterior);
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>("Noticia no encontrada",HttpStatus.NOT_FOUND);

    }

    /**
     * buscar por titulo
     * @param termino
     * @return
     */
    @GetMapping("/buscar/{termino}")
    public ResponseEntity<?>buscarPorTitulo(@PathVariable String termino){
        return ResponseEntity.ok().body(service.buscarPorTitulo(termino));
    }

    /**
     * guardar una foto
     * @param archivo
     * @param id
     * @return
     */
    @PostMapping("noticia/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo , @RequestParam("id") Long id){
        Map<String,Object> respuesta = new HashMap<>();

        Optional<Noticia> r = service.ver(id);
        Noticia noticia=null;
        if (r.isPresent()){
            noticia= r.get();
            if (!archivo.isEmpty()){
                String nombreArchivo = null;
                try {
                    nombreArchivo=fotoService.copiar(archivo);
                } catch (IOException e) {
                    respuesta.put("error",e.getMessage()+ " ");
                    respuesta.put("mensaje", "error al cargar la foto");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
                }
                String nombreFotoAnterior = noticia.getFoto();
                fotoService.eliminar(nombreFotoAnterior);

                noticia.setFoto(nombreArchivo);
                service.save(noticia);
                respuesta.put("noticia", noticia);
                respuesta.put("mensaje", "Ha subido correctamente la imagen"+ nombreArchivo );

            }

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * ver foto
     * @param nombreFoto
     * @return
     */
    @GetMapping("/upload/img/{nombreFoto}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Resource recurso = null;
        try {
            recurso=  fotoService.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ recurso.getFilename()+"\"");
        return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);

    }


}