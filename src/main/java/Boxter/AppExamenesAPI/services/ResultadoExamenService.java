package Boxter.AppExamenesAPI.services;

import Boxter.AppExamenesAPI.entity.ResultadoExamen;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.List;

public interface ResultadoExamenService {

    public ResponseEntity<?> obtenerResultadosDeExamenes(Long usuarioId);

}
