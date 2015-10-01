package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class TransaccionInternaEntity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @NotNull
    @Type(type = "org.hibernate.type.LocalDateType")
    protected Date fecha;

    @NotNull
    @Type(type = "org.hibernate.type.LocalTimeType")
    protected Date hora;

    @Size(min = 1, max = 60)
    protected String observacion;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    protected boolean estadoSolicitud;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    protected boolean estadoConfirmacion;

    @Version
    protected Timestamp optlk;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(boolean estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public boolean isEstadoConfirmacion() {
        return estadoConfirmacion;
    }

    public void setEstadoConfirmacion(boolean estadoConfirmacion) {
        this.estadoConfirmacion = estadoConfirmacion;
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
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TransaccionInternaEntity))
            return false;
        TransaccionInternaEntity other = (TransaccionInternaEntity) obj;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        return true;
    }

}
