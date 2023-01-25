package Boxter.AppExamenesAPI;

import Boxter.AppExamenesAPI.entity.Rol;
import Boxter.AppExamenesAPI.entity.Usuario;
import Boxter.AppExamenesAPI.entity.UsuarioRol;
import Boxter.AppExamenesAPI.exceptions.UsuarioNotFoundException;
import Boxter.AppExamenesAPI.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AppExamenesApiApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AppExamenesApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Crear Usuario Admin Al crear BBDD
		/*try {
			Usuario usuario = new Usuario();
			usuario.setNombreCompleto("admin");
			usuario.setUsername("admin");
			usuario.setPassword(passwordEncoder.encode("admin12345"));
			usuario.setEmail("admin@gmail.com");
			usuario.setTelefono("");

			Rol rol = new Rol();
			rol.setRolId(1L);
			rol.setRolNombre("ADMIN");

			Set<UsuarioRol> usuariosRoles = new HashSet<>();
			UsuarioRol usuarioRol = new UsuarioRol();
			usuarioRol.setRol(rol);
			usuarioRol.setUsuario(usuario);
			usuariosRoles.add(usuarioRol);

			Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario,usuariosRoles);
			System.out.println(usuarioGuardado.getUsername());
		}catch (UsuarioNotFoundException e){
			e.printStackTrace();
		}*/
	}
}
