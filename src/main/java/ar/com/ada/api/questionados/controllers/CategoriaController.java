package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.questionados.dto.CategoriaDTO;
import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.models.request.InfoPreguntaNueva;
import ar.com.ada.api.questionados.models.response.GenericResponse;
import ar.com.ada.api.questionados.services.CategoriaService;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @GetMapping("/categorias") //hacer el mapping
    public ResponseEntity<List<Categoria>> traerCategorias() { //return Response Entity
        return ResponseEntity.ok(service.traerCategorias()); //return entity con el valor esperado
    }
 
    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> traerCategoriaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarCategoria(id));
    }
 
    @PostMapping("/categorias")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        GenericResponse gr = new GenericResponse();
 
        if (service.crearCategoria(categoria)) {
             gr.id = categoria.getCategoriaId();
             gr.isOk = true;
             gr.message = "Categoria creada con exito";
             return ResponseEntity.ok(gr);
        } else {
             gr.isOk = false;
             gr.message = "Esta categoria ya esta creada";
             return ResponseEntity.badRequest().body(gr); // Error 400 + json especificando el problema
        }
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<?> modificarCategoria(@PathVariable Integer id,@RequestBody CategoriaDTO categoriaAModificar){
        Categoria categoria = service.buscarCategoria(id);
        service.modificarCategoria(categoria, categoriaAModificar);
        GenericResponse gr = new GenericResponse();
        gr.isOk = true;
        gr.id = id;
        gr.message = "El nombre y/o descripción de la categoria fue modificada con exito";
        return ResponseEntity.ok(gr);
    }

    @DeleteMapping("categorias/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Integer id){
        Categoria categoria = service.buscarCategoria(id);
        GenericResponse gr = new GenericResponse();
        if(service.eliminarCategoriaSinPreguntas(categoria)){
            gr.isOk = true;
            gr.id = id;
            gr.message = "La categoria ha sido eliminada con exito";
            return ResponseEntity.ok(gr);
        } else {
            gr.isOk = false;
            gr.id = id;
            gr.message = "La categoria no puede ser eliminada porque cuenta con preguntas asociadas";
            return ResponseEntity.badRequest().body(gr);
        }
    }

    @GetMapping("/categorias/{id}/preguntas") 
    public ResponseEntity<List<Pregunta>> traerPreguntasDeCategoria(@PathVariable Integer id){
        Categoria categoria = service.buscarCategoria(id);
        return ResponseEntity.ok(categoria.getPreguntas());
    }
   
}
