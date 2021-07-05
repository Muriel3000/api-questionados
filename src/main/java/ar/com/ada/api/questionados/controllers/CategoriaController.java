package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.models.request.InfoPreguntaNueva;
import ar.com.ada.api.questionados.models.response.GenericResponse;
import ar.com.ada.api.questionados.services.CategoriaService;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaService service;

    //Put
    //bad request -> ya existe, error 400, agregar json especificando

    //GET /categorias
    @GetMapping("/categorias") //hacer el mapping
    public ResponseEntity<List<Categoria>> traerCategorias() { //return Response Entity
        return ResponseEntity.ok(service.traerCategorias()); //return entity con el valor esperado
    }
 
    //GET Categoría por Id
    @GetMapping("/categoria/{id}")
    public ResponseEntity<Categoria> traerCategoriaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarCategoria(id));
    }
 
    @PostMapping(value = "/categoria")
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
             return ResponseEntity.badRequest().body(gr);
        }
    }
   
}
