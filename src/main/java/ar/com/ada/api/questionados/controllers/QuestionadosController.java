package ar.com.ada.api.questionados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.services.QuestionadosService;

public class QuestionadosController {

    @Autowired
    QuestionadosService service;

    @GetMapping("/questionados/next")
    public ResponseEntity<Pregunta> traerPreguntaRandom(){

        Pregunta proximaPregunta = service.traerPreguntaRandom();

        return ResponseEntity.ok(proximaPregunta);

        //return ResponseEntity.ok(service.traerPreguntaRandom());


        
    }
    
    //GET /questionados/preguntas
   

    //GET /questionados/preguntas/categoria/{catId}/next*/

}
