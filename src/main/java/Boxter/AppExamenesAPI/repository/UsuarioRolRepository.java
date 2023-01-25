package Boxter.AppExamenesAPI.repository;

import Boxter.AppExamenesAPI.entity.Usuario;
import Boxter.AppExamenesAPI.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol,Long> {

    public UsuarioRol findByUsuario(Usuario usuario);

}
