package Boxter.AppExamenesAPI.services;

import Boxter.AppExamenesAPI.entity.Categoria;
import Boxter.AppExamenesAPI.entity.Examen;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface ExamenService {

    Examen agregarExamen(Examen examen);

    ResponseEntity<?> actualizarExamen(Examen examen);

    Set<Examen> obtenerExamenes();

    List<Examen> listarExamenesDeUnaCategoria(Categoria categoria);

    List<Examen> listarExamenesActivosDeUnaCategoria(Long categoriaId);

    Examen obtenerExamen(Long examenId);

    void eliminarExamen(Long examenId);

}
