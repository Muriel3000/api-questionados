package ar.com.ada.api.questionados.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.repos.PreguntaRepository;

@Service
public class PreguntaService {
    
    @Autowired
    PreguntaRepository repo;
}
