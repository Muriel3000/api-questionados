package ar.com.ada.api.questionados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Pregunta;

@Service
public class QuestionadosService {
    
    @Autowired
    PreguntaService preguntaService;

    public Pregunta traerPreguntaRandom() {

        List<Pregunta> todasLasPreguntas = preguntaService.traerPreguntas();
        int min = 1;
        int max = todasLasPreguntas.size();
        int random = (int) (Math.random() * ((max - min) + 1)) + min;
        //return this.hechizos.get(random - 1);
        return todasLasPreguntas.get(random - 1);

    }
    
}
