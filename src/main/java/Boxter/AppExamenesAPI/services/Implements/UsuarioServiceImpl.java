package Boxter.AppExamenesAPI.services.Implements;

import Boxter.AppExamenesAPI.entity.Rol;
import Boxter.AppExamenesAPI.entity.Usuario;
import Boxter.AppExamenesAPI.entity.UsuarioRol;
import Boxter.AppExamenesAPI.repository.RolRepository;
import Boxter.AppExamenesAPI.repository.UsuarioRepository;
import Boxter.AppExamenesAPI.repository.UsuarioRolRepository;
import Boxter.AppExamenesAPI.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.http.HttpStatus.*;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usuario guardarUsuario(Usuario usuario) throws Exception {
        Map<String,Object> response = new HashMap<>();
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Rol rol = new Rol();
        rol.setRolId(usuario.getUsuarioRol().getRol().getRolId());
        rol.setRolNombre(usuario.getUsuarioRol().getRol().getRolNombre());

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);


        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }
        else{
            for(UsuarioRol usuarioRole:usuarioRoles){
                rolRepository.save(usuarioRole.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    //Crear Usuario Admin Al crear BBDD
    /*@Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }
        else{
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }*/

    @Override
    public ResponseEntity<?> actualizarUsuario(Usuario usuario) {

        Map<String,Object> response = new HashMap<>();
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        usuarioRoles.add(usuario.getUsuarioRol());

        for(UsuarioRol usuarioRol:usuarioRoles){
            UsuarioRol actualizarUsuarioRol = usuarioRolRepository.findById(usuarioRol.getUsuarioRolId()).orElse(null);
            actualizarUsuarioRol.setUsuario(usuario);
            actualizarUsuarioRol.setRol(usuarioRol.getRol());
        }
        usuario.getUsuarioRoles().addAll(usuarioRoles);

        Usuario buscarUsuario = usuarioRepository.findById(usuario.getId()).orElse(null);
        UsuarioRol usuarioRol = usuario.getUsuarioRol();

        Usuario actualizarUsuario = null;

        if(buscarUsuario == null){
            response.put("message","Error al actualizar Usuario, el Usuario con ID: ".concat(usuario.getId().toString()).concat(" no existe"));
            return new ResponseEntity<Map<String,Object>>(response, NOT_FOUND);
        }

        try {

            buscarUsuario.setUsername(usuario.getUsername());
            buscarUsuario.setFoto(usuario.getFoto());
            buscarUsuario.setPassword(usuario.getPassword());
            buscarUsuario.setTelefono(usuario.getTelefono());
            buscarUsuario.setEmail(usuario.getEmail());
            buscarUsuario.setNombreCompleto(usuario.getNombreCompleto());
            buscarUsuario.setEnabled(usuario.isEnabled());

            actualizarUsuario = usuarioRepository.save(buscarUsuario);

        }catch (DataAccessException e){
            response.put("message","Error al actualizar Usuario");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, INTERNAL_SERVER_ERROR);
        }

        response.put("message","Usuario Actualizado Exitosamente");
        response.put("usuario",usuario);

        return new ResponseEntity<Map<String ,Object>>(response, CREATED);
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public Usuario finById(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElse(null);
    }

    @Override
    public Usuario guardarImagen(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

}
