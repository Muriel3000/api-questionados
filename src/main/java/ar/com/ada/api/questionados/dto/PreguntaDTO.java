package ar.com.ada.api.questionados.dto;

import java.util.List;

import ar.com.ada.api.questionados.entities.Categoria;

public class PreguntaDTO {
    public Integer preguntaId;
    public String enunciado;
    public Categoria categoria;
    public List<RespuestaDTO> opciones;
}
