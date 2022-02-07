/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhoan
 */
@Entity
@Table(name = "fecso_cabsol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FecsoCabsol.findAll", query = "SELECT f FROM FecsoCabsol f")
    , @NamedQuery(name = "FecsoCabsol.findByFecsoNumerosoli", query = "SELECT f FROM FecsoCabsol f WHERE f.fecsoNumerosoli = :fecsoNumerosoli")
    , @NamedQuery(name = "FecsoCabsol.findByFecsoFecha", query = "SELECT f FROM FecsoCabsol f WHERE f.fecsoFecha = :fecsoFecha")
    , @NamedQuery(name = "FecsoCabsol.findByFecsoEstado", query = "SELECT f FROM FecsoCabsol f WHERE f.fecsoEstado = :fecsoEstado")})
public class FecsoCabsol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECSO_NUMEROSOLI")
    private Short fecsoNumerosoli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECSO_FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecsoFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECSO_ESTADO")
    private boolean fecsoEstado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fecsoCabsol")
    private FedsoDetsol fedsoDetsol;
    @JoinColumn(name = "FEEMP_CODIGO", referencedColumnName = "FEEMP_CODIGO")
    @ManyToOne(optional = false)
    private FeempEmple feempCodigo;

    public FecsoCabsol() {
    }

    public FecsoCabsol(Short fecsoNumerosoli) {
        this.fecsoNumerosoli = fecsoNumerosoli;
    }

    public FecsoCabsol(Short fecsoNumerosoli, Date fecsoFecha, boolean fecsoEstado) {
        this.fecsoNumerosoli = fecsoNumerosoli;
        this.fecsoFecha = fecsoFecha;
        this.fecsoEstado = fecsoEstado;
    }

    public Short getFecsoNumerosoli() {
        return fecsoNumerosoli;
    }

    public void setFecsoNumerosoli(Short fecsoNumerosoli) {
        this.fecsoNumerosoli = fecsoNumerosoli;
    }

    public Date getFecsoFecha() {
        return fecsoFecha;
    }

    public void setFecsoFecha(Date fecsoFecha) {
        this.fecsoFecha = fecsoFecha;
    }

    public boolean getFecsoEstado() {
        return fecsoEstado;
    }

    public void setFecsoEstado(boolean fecsoEstado) {
        this.fecsoEstado = fecsoEstado;
    }

    public FedsoDetsol getFedsoDetsol() {
        return fedsoDetsol;
    }

    public void setFedsoDetsol(FedsoDetsol fedsoDetsol) {
        this.fedsoDetsol = fedsoDetsol;
    }

    public FeempEmple getFeempCodigo() {
        return feempCodigo;
    }

    public void setFeempCodigo(FeempEmple feempCodigo) {
        this.feempCodigo = feempCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecsoNumerosoli != null ? fecsoNumerosoli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FecsoCabsol)) {
            return false;
        }
        FecsoCabsol other = (FecsoCabsol) object;
        if ((this.fecsoNumerosoli == null && other.fecsoNumerosoli != null) || (this.fecsoNumerosoli != null && !this.fecsoNumerosoli.equals(other.fecsoNumerosoli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.FecsoCabsol[ fecsoNumerosoli=" + fecsoNumerosoli + " ]";
    }
    
}
