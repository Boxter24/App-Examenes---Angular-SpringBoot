package Boxter.AppExamenesAPI.controllers;

import Boxter.AppExamenesAPI.entity.IntentoExamen;
import Boxter.AppExamenesAPI.services.IntentoExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/intentosExamenes")
@CrossOrigin("*")
public class IntentoExamenController {

    @Autowired
    private IntentoExamenService intentoExamenService;

    @GetMapping("/{examenId}/{usuarioId}")
    public BigInteger conteoIntentosExamen(@PathVariable("examenId") Long examenId, @PathVariable("usuarioId") Long usuarioId){
        return intentoExamenService.conteoIntentosExamen(examenId,usuarioId);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> conteoIntentosGenerales(@PathVariable("usuarioId") Long usuarioId){
        return intentoExamenService.conteoIntentosGenerales(usuarioId);
    }

    @PostMapping
    public ResponseEntity<?> guardarIntentoExamen(@RequestBody IntentoExamen intentoExamen){
        return intentoExamenService.guardarIntentoExamen(intentoExamen);
    }


}
