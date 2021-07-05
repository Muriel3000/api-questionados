package ar.com.ada.api.questionados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.*;
import ar.com.ada.api.questionados.repos.PreguntaRepository;

@Service
public class PreguntaService {
    
    @Autowired
    PreguntaRepository repository;

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
}
