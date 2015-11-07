package org.sistcoop.certamb.models.jpa.entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class HistorialEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    protected String id;  

    @NotNull
    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name = "FECHA_APERTURA")
    protected LocalDate fechaApertura;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name = "FECHA_CIERRE")
    protected LocalDate fechaCierre;

    @NotNull
    @Type(type = "org.hibernate.type.LocalTimeType")
    @Column(name = "HORA_APERTURA")
    protected LocalTime horaApertura;

    @Type(type = "org.hibernate.type.LocalTimeType")
    @Column(name = "HORA_CIERRE")
    protected LocalTime horaCierre;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "ABIERTO")
    protected boolean abierto;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "ESTADO_MOVIMIENTO")
    protected boolean estadoMovimiento;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "ESTADO")
    protected boolean estado;

    @Version
    private Timestamp optlk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    public boolean isAbierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public boolean isEstadoMovimiento() {
        return estadoMovimiento;
    }

    public void setEstadoMovimiento(boolean estadoMovimiento) {
        this.estadoMovimiento = estadoMovimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Timestamp getOptlk() {
        return optlk;
    }

    public void setOptlk(Timestamp optlk) {
        this.optlk = optlk;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HistorialEntity other = (HistorialEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
