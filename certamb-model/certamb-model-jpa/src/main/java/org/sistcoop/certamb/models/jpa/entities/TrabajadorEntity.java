package org.sistcoop.certamb.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TRABAJADOR")
@NamedQueries(value = {
        @NamedQuery(name = "TrabajadorEntity.findAll", query = "SELECT t FROM TrabajadorEntity t"),
        @NamedQuery(name = "TrabajadorEntity.findByTipoNumeroDocumento", query = "SELECT t FROM TrabajadorEntity t WHERE t.tipoDocumento = :tipoDocumento AND t.numeroDocumento = :numeroDocumento"),
        @NamedQuery(name = "TrabajadorEntity.findByUsuario", query = "SELECT t FROM TrabajadorEntity t WHERE UPPER(t.usuario) = UPPER(:usuario)"),
        @NamedQuery(name = "TrabajadorEntity.findByIdDireccionRegional", query = "SELECT t FROM TrabajadorEntity t INNER JOIN t.direccionRegional d WHERE d.id = :idDireccionRegional") })
public class TrabajadorEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @NotNull
    @Size(min = 1, max = 20)
    @NotBlank
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    @NotNull
    @Size(min = 1, max = 20)
    @NotBlank
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;

    @Size(min = 1, max = 100)
    @Column(name = "USUARIO")
    private String usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIRECCION_REGIONAL_ID", foreignKey = @ForeignKey )
    private DireccionRegionalEntity direccionRegional;

    @Version
    private Timestamp optlk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public DireccionRegionalEntity getDireccionRegional() {
        return direccionRegional;
    }

    public void setDireccionRegional(DireccionRegionalEntity direccionRegional) {
        this.direccionRegional = direccionRegional;
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
        result = prime * result + ((numeroDocumento == null) ? 0 : numeroDocumento.hashCode());
        result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TrabajadorEntity))
            return false;
        TrabajadorEntity other = (TrabajadorEntity) obj;
        if (numeroDocumento == null) {
            if (other.numeroDocumento != null)
                return false;
        } else if (!numeroDocumento.equals(other.numeroDocumento))
            return false;
        if (tipoDocumento == null) {
            if (other.tipoDocumento != null)
                return false;
        } else if (!tipoDocumento.equals(other.tipoDocumento))
            return false;
        return true;
    }

}
