package Boxter.AppExamenesAPI.controllers;

import Boxter.AppExamenesAPI.entity.Examen;
import Boxter.AppExamenesAPI.entity.Pregunta;
import Boxter.AppExamenesAPI.services.ExamenService;
import Boxter.AppExamenesAPI.services.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pregunta")
@CrossOrigin("*")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private ExamenService examenService;

    @PostMapping
    public ResponseEntity<Pregunta> guardarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));
    }

    @PutMapping
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }

    @GetMapping("/examen/{examenId}")
    public ResponseEntity<?> listarPreguntasDelExamen(@PathVariable("examenId") Long examenId){

        Examen examen = examenService.obtenerExamen(examenId);

        Set<Pregunta> preguntas = examen.getPreguntas();

        List examenes = new ArrayList(preguntas);

        if(examenes.size() > examen.getNumeroDePreguntas()){
            examenes = examenes.subList(0,examen.getNumeroDePreguntas());
        }

        Collections.shuffle(examenes);

        return ResponseEntity.ok(examenes);

    }

    @GetMapping("/{preguntaId}")
    public Pregunta listarPreguntaPorId(@PathVariable("preguntaId") Long preguntaId){
        return preguntaService.obtenerPregunta(preguntaId);
    }

    @DeleteMapping("/{preguntaId}")
    public void eliminarPregunta(@PathVariable("preguntaId") Long preguntaId){
        preguntaService.eliminarPregunta(preguntaId);
    }

}
