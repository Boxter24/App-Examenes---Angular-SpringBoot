package Boxter.AppExamenesAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "resultados_examenes")
@Data
public class ResultadoExamen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultadoExamenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examen_id", referencedColumnName = "examenId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Examen examen;

    private Long resultado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

}
