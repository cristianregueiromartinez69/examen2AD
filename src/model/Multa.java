package model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "multas")
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcoche")
    private Coche idcoche;

    @Column(name = "importe", precision = 10, scale = 2)
    private BigDecimal importe;

    @Column(name = "porcentaxereduccion")
    private Integer porcentaxereduccion;

    public Multa(Integer id, Coche idcoche, BigDecimal importe, Integer porcentaxereduccion) {
        this.id = id;
        this.idcoche = idcoche;
        this.importe = importe;
        this.porcentaxereduccion = porcentaxereduccion;
    }

    public Multa(Coche idcoche, BigDecimal importe, Integer porcentaxereduccion) {
        this.idcoche = idcoche;
        this.importe = importe;
        this.porcentaxereduccion = porcentaxereduccion;
    }

    public Multa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Coche getIdcoche() {
        return idcoche;
    }

    public void setIdcoche(Coche idcoche) {
        this.idcoche = idcoche;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Integer getPorcentaxereduccion() {
        return porcentaxereduccion;
    }

    public void setPorcentaxereduccion(Integer porcentaxereduccion) {
        this.porcentaxereduccion = porcentaxereduccion;
    }

    @Override
    public String toString() {
        return "\nMulta: " +
                "\nid: " + id +
                "\nidcoche: " + idcoche +
                "\nimporte: " + importe +
                "\nporcentaxereduccion: " + porcentaxereduccion;
    }
}