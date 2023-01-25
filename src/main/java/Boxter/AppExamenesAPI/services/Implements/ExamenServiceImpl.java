package Boxter.AppExamenesAPI.services.Implements;

import Boxter.AppExamenesAPI.entity.Categoria;
import Boxter.AppExamenesAPI.entity.Examen;
import Boxter.AppExamenesAPI.entity.Pregunta;
import Boxter.AppExamenesAPI.repository.ExamenRepository;
import Boxter.AppExamenesAPI.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.springframework.http.HttpStatus.*;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Override
    @Transactional
    public Examen agregarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    @Transactional
    public ResponseEntity<?> actualizarExamen(Examen examen) {
        Examen buscarExamen = examenRepository.findById(examen.getExamenId()).orElse(null);
        Map<String,Object> response = new HashMap<>();

        Examen actualizarExamen = null;

        if(buscarExamen == null){
            response.put("message","Error al actualizar Examen, el Examen con ID: ".concat(examen.getExamenId().toString()).concat(" no existe"));
            return new ResponseEntity<Map<String,Object>>(response, NOT_FOUND);
        }

        try {

            buscarExamen.setTitulo(examen.getTitulo());
            buscarExamen.setDescripcion(examen.getDescripcion());
            buscarExamen.setPuntosMaximos(examen.getPuntosMaximos());
            buscarExamen.setNumeroDePreguntas(examen.getNumeroDePreguntas());
            buscarExamen.setIntentos(examen.getIntentos());
            buscarExamen.setActivo(examen.isActivo());
            buscarExamen.setCategoria(examen.getCategoria());

            actualizarExamen = examenRepository.save(buscarExamen);

        }catch (DataAccessException e){
            response.put("message","Error al actualizar Examen");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, INTERNAL_SERVER_ERROR);
        }

        response.put("message","Examen Actualizado Exitosamente");
        response.put("Examen",actualizarExamen);

        return new ResponseEntity<Map<String ,Object>>(response, CREATED);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Examen> obtenerExamenes() {
        return new LinkedHashSet<>(examenRepository.findAll());
    }

    @Override
    public List<Examen> listarExamenesDeUnaCategoria(Categoria categoria) {
        return examenRepository.findAllByCategoria(categoria);
    }

    @Override
    public List<Examen> listarExamenesActivosDeUnaCategoria(Long categoriaId) {
        return examenRepository.findCategoriasActivas(categoriaId);
    }

    @Override
    @Transactional(readOnly = true)
    public Examen obtenerExamen(Long examenId) {
        return examenRepository.findById(examenId).get();
    }

    @Override
    @Transactional
    public void eliminarExamen(Long examenId) {
        examenRepository.deleteById(examenId);
    }

    @Override
    public Long buscarNumeroDePreguntas(Long examenId) {
        return examenRepository.findNumeroDePreguntas(examenId);
    }
}
