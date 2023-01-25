package Boxter.AppExamenesAPI.controllers;

import Boxter.AppExamenesAPI.entity.ResultadoExamen;
import Boxter.AppExamenesAPI.services.ResultadoExamenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/resultadosExamenes")
@CrossOrigin("*")
public class ResultadoExamenController {

    @Autowired
    private ResultadoExamenService resultadoExamenService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> obtenerResultadosDeExamenes(@PathVariable("usuarioId") Long usuarioId){
        return resultadoExamenService.obtenerResultadosDeExamenes(usuarioId);
    }

}
