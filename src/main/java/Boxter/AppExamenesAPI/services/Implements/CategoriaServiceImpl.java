package Boxter.AppExamenesAPI.services.Implements;

import Boxter.AppExamenesAPI.entity.Categoria;
import Boxter.AppExamenesAPI.repository.CategoriaRepository;
import Boxter.AppExamenesAPI.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

import static org.springframework.http.HttpStatus.*;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public Categoria agregarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public ResponseEntity<?> actualizarCategoria(Categoria categoria) {

        Categoria buscarCategoria = categoriaRepository.findById(categoria.getCategoriaId()).orElse(null);
        Map<String,Object> response = new HashMap<>();

        Categoria actualizarCategoria = null;

        if(buscarCategoria == null){
            response.put("message","Error al actualizar Categoria, la Categoria con ID: ".concat(categoria.getCategoriaId().toString()).concat(" no existe"));
            return new ResponseEntity<Map<String,Object>>(response, NOT_FOUND);
        }

        try {

            buscarCategoria.setTitulo(categoria.getTitulo());
            buscarCategoria.setDescripcion(categoria.getDescripcion());

            actualizarCategoria = categoriaRepository.save(buscarCategoria);

        }catch (DataAccessException e){
            response.put("message","Error al actualizar Categoria");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, INTERNAL_SERVER_ERROR);
        }

        response.put("message","Categoria Actualizada Exitosamente");
        response.put("Categoria",actualizarCategoria);

        return new ResponseEntity<Map<String ,Object>>(response, CREATED);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Categoria> obtenerCategorias() {
        return new LinkedHashSet<>(categoriaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria obtenerCategoria(Long categoriaId) {
        return categoriaRepository.findById(categoriaId).get();
    }

    @Override
    @Transactional
    public void eliminarCategoria(Long categoriaId) {
        categoriaRepository.deleteById(categoriaId);
    }
}
