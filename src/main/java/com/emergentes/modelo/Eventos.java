
package com.emergentes.modelo;

import java.sql.Date;

public class Eventos {
    private int id;
    private String titulo ;
    private String expositor;
    private String fecha;
    private String horas;
    private String cupos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getExpositor() {
        return expositor;
    }

    public void setExpositor(String expositor) {
        this.expositor = expositor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getCupos() {
        return cupos;
    }

    public void setCupos(String cupos) {
        this.cupos = cupos;
    }

    @Override
    public String toString() {
        return "Eventos{" + "id=" + id + ", titulo=" + titulo + ", expositor=" + expositor + ", fecha=" + fecha + ", horas=" + horas + ", cupos=" + cupos + '}';
    }
    
    
}
