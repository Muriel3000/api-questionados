package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.questionados.dto.PreguntaDTO;
import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.models.request.InfoPreguntaNueva;
import ar.com.ada.api.questionados.models.response.GenericResponse;
import ar.com.ada.api.questionados.services.PreguntaService;

@RestController
public class PreguntaController {
    
    @Autowired
    PreguntaService service;

    @GetMapping("/preguntas")
    public ResponseEntity<List<Pregunta>> traerPreguntas() {
        return ResponseEntity.ok(service.traerPreguntas());
    }

    @GetMapping("/preguntas/{id}")
    public ResponseEntity<Pregunta> buscarPreguntaPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscarPreguntaPorId(id));
    }

    @PostMapping ("/preguntas")
    public ResponseEntity<?> crearPregunta(@RequestBody InfoPreguntaNueva preguntaNueva){

        GenericResponse respuesta = new GenericResponse();
        Pregunta pregunta = service.crearPregunta(preguntaNueva.enunciado, preguntaNueva.categoriaId, preguntaNueva.opciones);
        respuesta.isOk = true;
        respuesta.id = pregunta.getPreguntaId();
        respuesta.message = "La pregunta fue creada con exito";

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/preguntas/{id}")
    public ResponseEntity<?> modificarPregunta(@PathVariable Integer id, @RequestBody PreguntaDTO preguntaAModificar){
        Pregunta pregunta = service.buscarPreguntaPorId(id);
        service.modificarPregunta(pregunta, preguntaAModificar);
        
        GenericResponse gr = new GenericResponse();
        gr.isOk = true;
        gr.id = id;
        gr.message = "La pregunta ha sido modificada con exito";
        return ResponseEntity.ok(gr);
    }

    @DeleteMapping("preguntas/{id}")
    public ResponseEntity<?> eliminarPreguntaYRespuestas(@PathVariable Integer id){
        Pregunta pregunta = service.buscarPreguntaPorId(id);
        service.eliminarPreguntaYRespuestas(pregunta);
        GenericResponse gr = new GenericResponse(true, id);
        gr.message = "La pregunta y sus respuestas han sido eliminadas";
        return ResponseEntity.ok(gr);
    }
    
}
