package apifestivo.apifestivo.core.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "festivos")
public class Festivos {

    @Id
    @Column(name = "id", nullable = false)
    private int id;


    @Column(name = "dia", nullable = false)
    private int dia;

    @Column(name = "mes",nullable = false)
    private int mes;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "id")
    private Tipo tipo;

    @Column(name = "diaspascua",nullable = false)
    private int diasPascua;

    public Festivos() {
    }

    public Festivos(int dia, int mes, String nombre, Festivos festivos, int diasPascua, Tipo tipo) {
        this.dia = dia;
        this.mes = mes;
        this.nombre = nombre;
        this.tipo = tipo;
        this.diasPascua = diasPascua;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getDiasPascua() {
        return diasPascua;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setDiasPascua(int diasPascua) {
        this.diasPascua = diasPascua;
    }

}