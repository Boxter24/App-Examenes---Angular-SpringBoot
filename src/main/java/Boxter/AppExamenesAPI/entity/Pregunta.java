package Boxter.AppExamenesAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "preguntas")
@Getter
@Setter
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preguntaId;

    @Column(length = 5000)
    private String contenido;

    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;

    @Transient
    private String respuestaDada;

    private String respuesta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "examen_id", referencedColumnName = "examenId")
    private Examen examen;

    public Pregunta(){}

}
