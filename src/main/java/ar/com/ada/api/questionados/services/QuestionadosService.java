package ar.com.ada.api.questionados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.entities.Respuesta;

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

    public boolean verificarRespuesta(Integer preguntaId, Integer respuestaId){
        Pregunta pregunta = preguntaService.buscarPreguntaPorId(preguntaId);
        for(Respuesta r : pregunta.getOpciones()){
            if(r.getRepuestaId().equals(respuestaId)){
               return r.isEsCorrecta();
                /* if(r.isEsCorrecta()
                    return true; */
            }
        }
        return false;
    }
    
}
