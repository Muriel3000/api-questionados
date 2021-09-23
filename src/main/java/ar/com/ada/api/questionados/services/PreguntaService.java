package ar.com.ada.api.questionados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.dto.PreguntaDTO;
import ar.com.ada.api.questionados.dto.RespuestaDTO;
import ar.com.ada.api.questionados.entities.*;
import ar.com.ada.api.questionados.repos.PreguntaRepository;
import ar.com.ada.api.questionados.repos.RespuestaRepository;

@Service
public class PreguntaService {
    
    @Autowired
    PreguntaRepository repository;

    @Autowired 
    RespuestaRepository respuestaRepo;

    @Autowired
    CategoriaService categoriaService;

    // buscarPregunta
    
    public Pregunta buscarPreguntaPorId(Integer preguntaId) {

        Optional<Pregunta> resultado = repository.findById(preguntaId);

        if (resultado.isPresent())
            return resultado.get();

        return null;
    }

    // traerPreguntas

    public List<Pregunta> traerPreguntas() {
        return repository.findAll();
    }

    // crearPregunta
    
    public Pregunta crearPregunta(String enunciado, Integer categoriaId, List<Respuesta> opciones ) {
        
        Pregunta pregunta = new Pregunta();
        pregunta.setEnunciado(enunciado);

        Categoria categoria = categoriaService.buscarCategoria(categoriaId);

        pregunta.setCategoria(categoria);
      
        for (Respuesta respuesta: opciones) {
            respuesta.setPregunta(pregunta);
        }
        
        repository.save(pregunta);
        return pregunta;
    }

    // modificarPreguntaYRespuestas*

    public void modificarPregunta(Pregunta pregunta, PreguntaDTO preguntaDTO){
        if(preguntaDTO.enunciado != null){
            pregunta.setEnunciado(preguntaDTO.enunciado);
        }
        if(preguntaDTO.categoria.getCategoriaId() != null){
            Categoria categoria = categoriaService.buscarCategoria(preguntaDTO.categoria.getCategoriaId());
            pregunta.setCategoria(categoria);
        }
        for(RespuestaDTO r : preguntaDTO.opciones){
            for(Respuesta respuesta : pregunta.getOpciones()){
                if(r.respuestaId == respuesta.getRespuestaId()){
                    if(r.texto != null){
                        respuesta.setTexto(r.texto);
                    }
                }
            }       
        }
        repository.save(pregunta);
    }     

    // eliminarPreguntaYRespuestas*

    public void eliminarPreguntaYRespuestas(Pregunta pregunta){
        for(Respuesta respuesta : pregunta.getOpciones()){
            respuestaRepo.delete(respuesta);
        }
        repository.delete(pregunta);
    }
}
