package Boxter.AppExamenesAPI.services.Implements;

import Boxter.AppExamenesAPI.entity.Rol;
import Boxter.AppExamenesAPI.entity.Usuario;
import Boxter.AppExamenesAPI.entity.UsuarioRol;
import Boxter.AppExamenesAPI.repository.UsuarioRolRepository;
import Boxter.AppExamenesAPI.services.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.springframework.http.HttpStatus.*;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService {

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Override
    @Transactional(readOnly = true)
    public UsuarioRol obtenerRolDeUsuario(Usuario usuario) {
        return usuarioRolRepository.findByUsuario(usuario);
    }

}
