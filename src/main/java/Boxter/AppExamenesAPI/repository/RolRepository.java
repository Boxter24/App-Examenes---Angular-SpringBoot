package Boxter.AppExamenesAPI.repository;

import Boxter.AppExamenesAPI.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
}
