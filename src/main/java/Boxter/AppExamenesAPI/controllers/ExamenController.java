package Boxter.AppExamenesAPI.controllers;

import Boxter.AppExamenesAPI.entity.Categoria;
import Boxter.AppExamenesAPI.entity.Examen;
import Boxter.AppExamenesAPI.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examen")
@CrossOrigin("*")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

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
        Long id = categoriaId;
        return examenService.listarExamenesActivosDeUnaCategoria(categoriaId);
    }

    @DeleteMapping("/{examenId}")
    public void eliminarCategoria(@PathVariable("examenId") Long examenId){
        examenService.eliminarExamen(examenId);
    }

}
