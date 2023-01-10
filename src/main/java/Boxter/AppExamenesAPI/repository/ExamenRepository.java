package Boxter.AppExamenesAPI.repository;

import Boxter.AppExamenesAPI.entity.Categoria;
import Boxter.AppExamenesAPI.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamenRepository extends JpaRepository<Examen,Long> {

    List<Examen> findAllByCategoria(Categoria categoria);

    String SQL_EXAMENES_ACTIVOS = "SELECT exam.examen_id, exam.activo,exam.titulo, exam.descripcion, exam.numero_de_preguntas, exam.puntos_maximos, exam.categoria_categoria_id " +
            "FROM app_examenes.examenes exam " +
            "INNER JOIN app_examenes.preguntas preg " +
            "ON (exam.categoria_categoria_id = :categoriaId) AND (exam.activo = 1) AND (exam.numero_de_preguntas > 0) AND (exam.examen_id = preg.examen_examen_id) " +
            "GROUP BY exam.examen_id";

    @Query(nativeQuery = true, value = SQL_EXAMENES_ACTIVOS)
    List<Examen> findCategoriasActivas(@Param("categoriaId") Long categoriaId);

}
