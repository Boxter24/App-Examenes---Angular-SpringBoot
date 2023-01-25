package Boxter.AppExamenesAPI.services.Implements;

import Boxter.AppExamenesAPI.entity.IntentoExamen;
import Boxter.AppExamenesAPI.repository.IntentoExamenRepository;
import Boxter.AppExamenesAPI.services.IntentoExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@Service
public class IntentoExamenServiceImpl implements IntentoExamenService {

    @Autowired
    private IntentoExamenRepository intentoExamenRepository;

    @Override
    public BigInteger conteoIntentosExamen(Long examenId, Long usuarioId) {
        return intentoExamenRepository.conteoIntentosExamen(examenId,usuarioId);
    }

    @Override
    public ResponseEntity<?> conteoIntentosGenerales(Long usuarioId) {

        Map<String,Object> response = new HashMap<>();

        Long conteoIntentosGenerales = intentoExamenRepository.conteoIntentosGenerales(usuarioId);

        response.put("conteo",conteoIntentosGenerales);

        return new ResponseEntity<Map<String ,Object>>(response, OK);
    }

    @Override
    public ResponseEntity<?> guardarIntentoExamen(IntentoExamen intentoExamen) {
        Map<String,Object> response = new HashMap<>();

        intentoExamenRepository.save(intentoExamen);

        response.put("mensaje","Se ha realizado un intento para el exámen");

        return new ResponseEntity<Map<String ,Object>>(response, OK);
    }
}
