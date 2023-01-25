package Boxter.AppExamenesAPI.services.Implements;

import Boxter.AppExamenesAPI.entity.ResultadoExamen;
import Boxter.AppExamenesAPI.entity.Usuario;
import Boxter.AppExamenesAPI.repository.ResultadoExamenRepository;
import Boxter.AppExamenesAPI.repository.UsuarioRepository;
import Boxter.AppExamenesAPI.services.ResultadoExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@Service
public class ResultadoExamenServiceImpl implements ResultadoExamenService {

    @Autowired
    private ResultadoExamenRepository resultadoExamenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<?> obtenerResultadosDeExamenes(Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        Map<String,Object> response = new HashMap<>();
        Long sumaIntentosGenerales = resultadoExamenRepository.sumaIntentosGenerales(usuarioId);

        response.put("suma",sumaIntentosGenerales);

        return new ResponseEntity<Map<String ,Object>>(response, OK);
    }

}
