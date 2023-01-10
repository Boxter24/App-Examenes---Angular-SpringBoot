package Boxter.AppExamenesAPI.services;

import Boxter.AppExamenesAPI.entity.Categoria;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface CategoriaService {

    Categoria agregarCategoria(Categoria categoria);

    ResponseEntity<?> actualizarCategoria(Categoria categoria);

    Set<Categoria> obtenerCategorias();

    Categoria obtenerCategoria(Long categoriaId);

    void eliminarCategoria(Long categoriaId);

}
