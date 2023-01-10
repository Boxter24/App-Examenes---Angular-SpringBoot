package Boxter.AppExamenesAPI.services;

import Boxter.AppExamenesAPI.entity.Examen;
import Boxter.AppExamenesAPI.entity.Pregunta;

import java.util.Set;

public interface PreguntaService {

    Pregunta agregarPregunta(Pregunta pregunta);

    Pregunta actualizarPregunta(Pregunta pregunta);

    Set<Pregunta> obtenerPreguntas();

    Pregunta obtenerPregunta(Long preguntaId);

    Set<Pregunta> obtenerPreguntasDelExamen(Examen examen);

    void eliminarPregunta(Long preguntaId);

}
