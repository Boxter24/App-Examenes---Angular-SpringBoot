package Boxter.AppExamenesAPI.services.Implements;

import Boxter.AppExamenesAPI.entity.Usuario;
import Boxter.AppExamenesAPI.entity.UsuarioRol;
import Boxter.AppExamenesAPI.repository.RolRepository;
import Boxter.AppExamenesAPI.repository.UsuarioRepository;
import Boxter.AppExamenesAPI.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
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
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
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
