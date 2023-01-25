package Boxter.AppExamenesAPI.repository;

import Boxter.AppExamenesAPI.entity.IntentoExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface IntentoExamenRepository extends JpaRepository<IntentoExamen,Long> {

    String SQL_CONTEO_INTENTOS = "SELECT count(intentos_examenes.examen_id) " +
            "FROM app_examenes.intentos_examenes intentos_examenes " +
            "WHERE (intentos_examenes.examen_id = :examenId) AND (intentos_examenes.usuario_id = :usuarioId);";

    @Query(nativeQuery = true, value = SQL_CONTEO_INTENTOS)
    BigInteger conteoIntentosExamen(@Param("examenId") Long examenId, @Param("usuarioId") Long usuarioId);



    String SQL_CONTEO_INTENTOS_GENERALES = "SELECT count(DISTINCT intentos_examenes.examen_id) " +
            "FROM app_examenes.intentos_examenes intentos_examenes " +
            "WHERE (intentos_examenes.usuario_id = :usuarioId)";

    @Query(nativeQuery = true, value = SQL_CONTEO_INTENTOS_GENERALES)
    Long conteoIntentosGenerales(@Param("usuarioId") Long usuarioId);

}
