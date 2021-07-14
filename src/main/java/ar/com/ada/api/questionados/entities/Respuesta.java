package ar.com.ada.api.questionados.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "respuesta")
public class Respuesta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "respuesta_id")
    private Integer repuestaId;

    @ManyToOne
    @JoinColumn(name = "pregunta_id", referencedColumnName = "pregunta_id")
    @JsonIgnore  //?? 
    private Pregunta pregunta;
   
    @Column
    private String texto;

    //JsonIgnore -> funciona en get, pero no se puede porque no te deja hacer put desde el front
    @Column(name = "es_correcta")
    private boolean esCorrecta;

    public Integer getRepuestaId() {
        return repuestaId;
    }

    public void setRepuestaId(Integer repuestaId) {
        this.repuestaId = repuestaId;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
        this.pregunta.agregarOpcion(this);
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

}
