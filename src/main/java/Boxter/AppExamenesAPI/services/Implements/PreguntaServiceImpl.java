package Boxter.AppExamenesAPI.services.Implements;

import Boxter.AppExamenesAPI.entity.Examen;
import Boxter.AppExamenesAPI.entity.Pregunta;
import Boxter.AppExamenesAPI.repository.PreguntaRepository;
import Boxter.AppExamenesAPI.services.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    @Transactional
    public Pregunta agregarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    @Transactional
    public Pregunta actualizarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Pregunta> obtenerPreguntas() {
        return (Set<Pregunta>) preguntaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pregunta obtenerPregunta(Long preguntaId) {
        return preguntaRepository.findById(preguntaId).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Pregunta> obtenerPreguntasDelExamen(Examen examen) {
        return preguntaRepository.findByExamen(examen);
    }

    @Override
    @Transactional
    public void eliminarPregunta(Long preguntaId) {
        preguntaRepository.deleteById(preguntaId);
    }
}
