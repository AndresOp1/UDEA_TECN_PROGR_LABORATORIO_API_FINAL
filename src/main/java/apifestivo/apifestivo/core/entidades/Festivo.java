package apifestivo.apifestivo.core.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "festivo")
public class Festivo {

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

    public Festivo() {
    }

    public Festivo(int dia, int mes, String nombre, Festivo festivos, int diasPascua, Tipo tipo, boolean esFestivo) {
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

    public void setId(int id) {
        this.id = id;
    }
    

    public void setFecha(Date date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFecha'");
    }

}