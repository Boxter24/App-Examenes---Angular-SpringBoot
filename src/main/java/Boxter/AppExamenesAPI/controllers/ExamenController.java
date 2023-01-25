package Boxter.AppExamenesAPI.controllers;

import Boxter.AppExamenesAPI.entity.Categoria;
import Boxter.AppExamenesAPI.entity.Examen;
import Boxter.AppExamenesAPI.entity.ResultadoExamen;
import Boxter.AppExamenesAPI.repository.ResultadoExamenRepository;
import Boxter.AppExamenesAPI.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/examen")
@CrossOrigin("*")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @Autowired
    private ResultadoExamenRepository resultadoExamenRepository;

    @PostMapping
    public ResponseEntity<Examen> guardarExamen(@RequestBody Examen examen){
        return ResponseEntity.ok(examenService.agregarExamen(examen));
    }

    @PutMapping
    public ResponseEntity<?> actualizarExamen(@RequestBody Examen examen){
        return examenService.actualizarExamen(examen);
    }

    @GetMapping
    public ResponseEntity<?> listarExamenes(){
        return ResponseEntity.ok(examenService.obtenerExamenes());
    }

    @GetMapping("/{examenId}")
    public Examen listarExamen(@PathVariable("examenId") Long examenId){
        return examenService.obtenerExamen(examenId);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Examen> listarExamenesDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){

        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);

        return examenService.listarExamenesDeUnaCategoria(categoria);
    }

    @GetMapping("/categoria/examenes/{categoriaId}")
    public List<Examen> listarExamenesActivosDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
        return examenService.listarExamenesActivosDeUnaCategoria(categoriaId);
    }

    @DeleteMapping("/{examenId}")
    public void eliminarCategoria(@PathVariable("examenId") Long examenId){
        examenService.eliminarExamen(examenId);
    }

    @GetMapping("/numeroDepreguntas/{examenId}")
    public Long buscarNumeroDePreguntas(@PathVariable("examenId") Long examenId){
        return examenService.buscarNumeroDePreguntas(examenId);
    }

    @PostMapping("/evaluar")
    public ResponseEntity<?> evaluarExamen(@RequestBody ResultadoExamen resultadoExamen){
        Map<String,Object> response = new HashMap<>();
        //utilizado para actualizar nota, de ésta forma se debe crear una tabla para registrar los intentos
        /*ResultadoExamen  buscarResultadoExamen = resultadoExamenRepository.buscarPuntajesExamen(resultadoExamen.getExamen().getExamenId(),resultadoExamen.getUsuario().getId());

        if(buscarResultadoExamen != null){
            if(resultadoExamen.getResultado() > buscarResultadoExamen.getResultado()){
                buscarResultadoExamen.setResultado(resultadoExamen.getResultado());

                resultadoExamenRepository.save(buscarResultadoExamen);

                response.put("mensaje","Resultado de Examen Actualizado con éxito.");
            }
        }
        else{
            resultadoExamenRepository.save(resultadoExamen);
            response.put("mensaje","Resultado de Examen guardado con éxito.");
        }*/
        resultadoExamenRepository.save(resultadoExamen);

        response.put("mensaje","Resultado de Examen guardado con éxito.");
        return new ResponseEntity<Map<String ,Object>>(response, CREATED);
    }

}
