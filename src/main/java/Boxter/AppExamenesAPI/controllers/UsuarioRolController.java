package Boxter.AppExamenesAPI.controllers;

import Boxter.AppExamenesAPI.entity.Usuario;
import Boxter.AppExamenesAPI.entity.UsuarioRol;
import Boxter.AppExamenesAPI.repository.UsuarioRepository;
import Boxter.AppExamenesAPI.services.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/usuarioRol")
@CrossOrigin("*")
public class UsuarioRolController {

    @Autowired
    private UsuarioRolService usuarioRolService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{usuarioId}")
    public UsuarioRol obtenerRolDeUsuario(@PathVariable("usuarioId") Long usuarioId){

        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        return usuarioRolService.obtenerRolDeUsuario(usuario);
    }

}
