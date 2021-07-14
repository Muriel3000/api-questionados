package ar.com.ada.api.questionados.models.response;

import java.util.List;

import ar.com.ada.api.questionados.entities.Categoria;

public class PreguntaAResolver {
    public Integer preguntaId;
    public String enunciado;
    public Categoria categoria;
    public List<OpcionPregunta> opciones;
}
