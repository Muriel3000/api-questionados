package ar.com.ada.api.questionados.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.repos.RespuestaRepository;

@Service
public class RespuestaService {
    
    @Autowired
    RespuestaRepository repo;
}
