package Boxter.AppExamenesAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "intentos_examenes")
@Data
public class IntentoExamen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long intentoExamenId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "examen_id", referencedColumnName = "examenId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Examen examen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

}
