package ar.com.ada.api.questionados.services;

import java.util.List;
import java.util.Optional;

import ar.com.ada.api.questionados.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ar.com.ada.api.questionados.repos.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository repository;

    // traerCategorias

    public List<Categoria> traerCategorias(){
        return repository.findAll(); 
    }

    // buscarCategoria

    /* public Categoria buscarCategoriaById(Integer categoriaId){
        Optional<Categoria> resultado = repository.findById(categoriaId);
        if(resultado.isPresent()){
            return resultado.get();
        }
        return null;
    } */

    public Categoria buscarCategoria(Integer categoriaId) {

        Optional<Categoria> resultado = repository.findById(categoriaId);
        Categoria categoria = null;

        if (resultado.isPresent())
            categoria = resultado.get();

        return categoria;

    }

    public Categoria buscarCategoriaV2(Integer categoriaId) {

        Categoria categoria = repository.findById(categoriaId.intValue());

        return categoria;

    }

    // crearCategoria

    /*public void crearCategoria(Categoria categoria){
        repository.save(categoria);
    } */

    public boolean crearCategoria(Categoria categoria) {
        if (existe(categoria.getNombre()))
            return false;

        repository.save(categoria);

        return true;
    }

    public boolean existe(String nombre) {
        Categoria categoria = repository.findByNombre(nombre);
        return categoria != null;
    }

    public boolean existeV2(String nombre) {
        return repository.existsByNombre(nombre);
    }

}
