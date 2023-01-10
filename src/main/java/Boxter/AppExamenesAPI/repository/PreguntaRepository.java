package Boxter.AppExamenesAPI.repository;

import Boxter.AppExamenesAPI.entity.Examen;
import Boxter.AppExamenesAPI.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta,Long> {

    Set<Pregunta> findByExamen(Examen examen);

}
