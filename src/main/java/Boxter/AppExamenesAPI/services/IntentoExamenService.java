package Boxter.AppExamenesAPI.services;

import Boxter.AppExamenesAPI.entity.IntentoExamen;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;

public interface IntentoExamenService {

    public BigInteger conteoIntentosExamen(Long examenId, Long usuarioId);

    public ResponseEntity<?> conteoIntentosGenerales(Long usuarioId);

    public ResponseEntity<?> guardarIntentoExamen(IntentoExamen intentoExamen);

}
