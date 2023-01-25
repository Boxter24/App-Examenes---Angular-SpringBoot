package Boxter.AppExamenesAPI.controllers;

import Boxter.AppExamenesAPI.entity.Rol;
import Boxter.AppExamenesAPI.entity.Usuario;
import Boxter.AppExamenesAPI.entity.UsuarioRol;
import Boxter.AppExamenesAPI.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {

        return usuarioService.guardarUsuario(usuario);
    }

    @PutMapping
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario){

        return usuarioService.actualizarUsuario(usuario);

    }

    @PostMapping("/imagen")
    public ResponseEntity<?> guardarImagenUsuario(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long usuarioId){

        Map<String,Object> response = new HashMap<>();

        Usuario usuario = usuarioService.finById(usuarioId);
        Usuario usuarioGuardado = null;

        if(!archivo.isEmpty()){

            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

            try {
                Files.copy(archivo.getInputStream(),rutaArchivo);
            }catch (IOException e){
                response.put("Mensaje","Error al guardar imagen");
                response.put("error",e.getMessage().concat(": ").concat(e.getCause().getMessage()));

                return new ResponseEntity<Map<String,Object>>(response, INTERNAL_SERVER_ERROR);
            }

            String nombreFotoAnterior = usuario.getFoto();

            if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0){
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                    archivoFotoAnterior.delete();
                }
            }

            usuario.setFoto(nombreArchivo);

            usuarioService.guardarImagen(usuario);

            response.put("archivo",nombreArchivo);
            response.put("mensaje","Imagen actualizada correctamente!!");
        }

        return new ResponseEntity<Map<String ,Object>>(response, CREATED);

    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){

        Usuario usuario = usuarioService.finById(usuarioId);

        String nombreFotoAnterior = usuario.getFoto();

        if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0){
            Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                archivoFotoAnterior.delete();
            }
        }

        usuarioService.eliminarUsuario(usuarioId);
    }

    @GetMapping("/imagen/{nombreImagen:.+}")
    public ResponseEntity<Resource> viewImage(@PathVariable String nombreImagen){

        Path rutaArchivo = Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
        Resource recurso = null;

        try{
            recurso = new UrlResource(rutaArchivo.toUri());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        if(!recurso.exists() && !recurso.isReadable()){
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("user-default.png").toAbsolutePath();

            try{
                recurso = new UrlResource(rutaArchivo.toUri());
            }catch (MalformedURLException e){
                e.printStackTrace();
            }

           log.error("Error al cargar la imagen: " + nombreImagen);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso,headers, OK);
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

}
