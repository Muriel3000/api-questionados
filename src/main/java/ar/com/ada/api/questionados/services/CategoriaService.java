package ar.com.ada.api.questionados.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.ada.api.questionados.repos.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository repo;
    
}
