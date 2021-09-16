package ar.com.ada.api.questionados.models.response;

import ar.com.ada.api.questionados.entities.Pregunta;

public class PreguntaSinCategoria {
    public Integer preguntaId;
    public String enunciado;

    public static PreguntaSinCategoria convertirDesde(Pregunta p){
        PreguntaSinCategoria pregunta = new PreguntaSinCategoria();
        pregunta.preguntaId = p.getPreguntaId();
        pregunta.enunciado = p.getEnunciado();
        return pregunta;
    }
}
