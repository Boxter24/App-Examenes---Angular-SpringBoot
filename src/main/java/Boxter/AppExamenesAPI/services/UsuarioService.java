package Boxter.AppExamenesAPI.services;

import Boxter.AppExamenesAPI.entity.Usuario;
import Boxter.AppExamenesAPI.entity.UsuarioRol;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario) throws Exception;

    //Crear Usuario Admin Al crear BBDD
    //public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    ResponseEntity<?> actualizarUsuario(Usuario usuario);

    public Usuario obtenerUsuario(String username);

    public List<Usuario> obtenerUsuarios();

    public void eliminarUsuario(Long usuarioId);

    public Usuario finById(Long usuarioId);

    public Usuario guardarImagen(Usuario usuario);

}
