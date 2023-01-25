package Boxter.AppExamenesAPI.repository;

import Boxter.AppExamenesAPI.entity.ResultadoExamen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ResultadoExamenRepository extends JpaRepository<ResultadoExamen,Long> {

    String SQL_BUSCAR_PUNTAJES_EXAMEN = "SELECT *" +
            "FROM app_examenes.resultados_examenes resultados_examenes " +
            "WHERE (resultados_examenes.examen_id = :examenId) " +
            "AND (resultados_examenes.usuario_id = :usuarioId) " +
            "AND (resultados_examenes.resultado = (SELECT MAX(resultados_examenes.resultado) " +
            "FROM app_examenes.resultados_examenes))";

    @Query(nativeQuery = true, value = SQL_BUSCAR_PUNTAJES_EXAMEN)
    ResultadoExamen buscarPuntajesExamen(@Param("examenId") Long examenId, @Param("usuarioId") Long usuarioId);


    String SQL_SUMA_INTENTOS_GENERALES = "SELECT sum(sumaResultadosExamenes) " +
            "FROM (SELECT max(resultados_examenes.resultado) sumaResultadosExamenes " +
                "FROM app_examenes.resultados_examenes resultados_examenes " +
                "WHERE (resultados_examenes.usuario_id = :usuarioId) " +
                "group by (examen_id)) as resultados_examenes;";

    @Query(nativeQuery = true, value = SQL_SUMA_INTENTOS_GENERALES)
    Long sumaIntentosGenerales(@Param("usuarioId") Long usuarioId);

}
