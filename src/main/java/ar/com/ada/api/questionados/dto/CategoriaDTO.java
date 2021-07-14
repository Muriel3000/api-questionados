package ar.com.ada.api.questionados.dto;

import java.util.List;

public class CategoriaDTO {
    public Integer categoriaId;
    public String nombre;
    public String descripcion;
    public List<PreguntaDTO> preguntas;
}
