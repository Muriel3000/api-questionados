package ar.com.ada.api.questionados.services;

import java.util.List;
import java.util.Optional;

import ar.com.ada.api.questionados.dto.CategoriaDTO;
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

    // modificarCategoria

    public boolean modificarCategoria(Categoria categoria, CategoriaDTO categoriaAModificar){
       if(modificarNombre(categoria, categoriaAModificar) || modificarDescripcion(categoria, categoriaAModificar)){
           return true;
        }  return false;
    }

    public boolean modificarNombre(Categoria categoria, CategoriaDTO categoriaAModificar) {
        if(categoriaAModificar.nombre != null){    //sin el if significa que debe tener un valor si o si(not null)
            categoria.setNombre(categoriaAModificar.nombre);
            repository.save(categoria);
            return true;
        }   return false;
    }

    public boolean modificarDescripcion(Categoria categoria, CategoriaDTO categoriaAModificar){
        if(categoriaAModificar.descripcion != null){
            categoria.setDescripcion(categoriaAModificar.descripcion);
            repository.save(categoria);
            return true;
        }   return false;
    }

    // eliminarCategoria
    
    public boolean eliminarCategoriaSinPreguntas(Categoria categoria) {
        if(categoria.getPreguntas().isEmpty()){
            repository.delete(categoria);
            return true;
        } else {
            return false;
        }
    }

}
