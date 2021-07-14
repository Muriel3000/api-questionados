package ar.com.ada.api.questionados.dto;

import ar.com.ada.api.questionados.entities.Pregunta;

public class RespuestaDTO {
    public Integer respuestaId;
    public String texto;
    public Pregunta pregunta;
    public boolean esCorrecta;
}
