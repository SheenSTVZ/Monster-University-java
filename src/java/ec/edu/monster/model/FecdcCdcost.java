/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhoan
 */
@Entity
@Table(name = "fecdc_cdcost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FecdcCdcost.findAll", query = "SELECT f FROM FecdcCdcost f")
    , @NamedQuery(name = "FecdcCdcost.findByFecdcCodigo", query = "SELECT f FROM FecdcCdcost f WHERE f.fecdcCodigo = :fecdcCodigo")
    , @NamedQuery(name = "FecdcCdcost.findByFecdcNombre", query = "SELECT f FROM FecdcCdcost f WHERE f.fecdcNombre = :fecdcNombre")
    , @NamedQuery(name = "FecdcCdcost.findByFecdcDescripcion", query = "SELECT f FROM FecdcCdcost f WHERE f.fecdcDescripcion = :fecdcDescripcion")})
public class FecdcCdcost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "FECDC_CODIGO")
    private String fecdcCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "FECDC_NOMBRE")
    private String fecdcNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "FECDC_DESCRIPCION")
    private String fecdcDescripcion;
    @OneToMany(mappedBy = "fecdcCodigo")
    private Collection<FeempEmple> feempEmpleCollection;

    public FecdcCdcost() {
    }

    public FecdcCdcost(String fecdcCodigo) {
        this.fecdcCodigo = fecdcCodigo;
    }

    public FecdcCdcost(String fecdcCodigo, String fecdcNombre, String fecdcDescripcion) {
        this.fecdcCodigo = fecdcCodigo;
        this.fecdcNombre = fecdcNombre;
        this.fecdcDescripcion = fecdcDescripcion;
    }

    public String getFecdcCodigo() {
        return fecdcCodigo;
    }

    public void setFecdcCodigo(String fecdcCodigo) {
        this.fecdcCodigo = fecdcCodigo;
    }

    public String getFecdcNombre() {
        return fecdcNombre;
    }

    public void setFecdcNombre(String fecdcNombre) {
        this.fecdcNombre = fecdcNombre;
    }

    public String getFecdcDescripcion() {
        return fecdcDescripcion;
    }

    public void setFecdcDescripcion(String fecdcDescripcion) {
        this.fecdcDescripcion = fecdcDescripcion;
    }

    @XmlTransient
    public Collection<FeempEmple> getFeempEmpleCollection() {
        return feempEmpleCollection;
    }

    public void setFeempEmpleCollection(Collection<FeempEmple> feempEmpleCollection) {
        this.feempEmpleCollection = feempEmpleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecdcCodigo != null ? fecdcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FecdcCdcost)) {
            return false;
        }
        FecdcCdcost other = (FecdcCdcost) object;
        if ((this.fecdcCodigo == null && other.fecdcCodigo != null) || (this.fecdcCodigo != null && !this.fecdcCodigo.equals(other.fecdcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.FecdcCdcost[ fecdcCodigo=" + fecdcCodigo + " ]";
    }
    
}
