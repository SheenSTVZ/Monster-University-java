/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "pepar_parro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeparParro.findAll", query = "SELECT p FROM PeparParro p")
    , @NamedQuery(name = "PeparParro.findByPepaisCodigo", query = "SELECT p FROM PeparParro p WHERE p.peparParroPK.pepaisCodigo = :pepaisCodigo")
    , @NamedQuery(name = "PeparParro.findByPeprovCodigo", query = "SELECT p FROM PeparParro p WHERE p.peparParroPK.peprovCodigo = :peprovCodigo")
    , @NamedQuery(name = "PeparParro.findByPecanCodigo", query = "SELECT p FROM PeparParro p WHERE p.peparParroPK.pecanCodigo = :pecanCodigo")
    , @NamedQuery(name = "PeparParro.findByPeparCodigo", query = "SELECT p FROM PeparParro p WHERE p.peparParroPK.peparCodigo = :peparCodigo")
    , @NamedQuery(name = "PeparParro.findByPeparDescri", query = "SELECT p FROM PeparParro p WHERE p.peparDescri = :peparDescri")})
public class PeparParro implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeparParroPK peparParroPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PEPAR_DESCRI")
    private String peparDescri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peparParro")
    private Collection<BealmAlmacn> bealmAlmacnCollection;
    @JoinColumns({
        @JoinColumn(name = "PEPAIS_CODIGO", referencedColumnName = "PEPAIS_CODIGO", insertable = false, updatable = false)
        , @JoinColumn(name = "PEPROV_CODIGO", referencedColumnName = "PEPROV_CODIGO", insertable = false, updatable = false)
        , @JoinColumn(name = "PECAN_CODIGO", referencedColumnName = "PECAN_CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PecanCanton pecanCanton;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peparParro")
    private Collection<FeempEmple> feempEmpleCollection;

    public PeparParro() {
    }

    public PeparParro(PeparParroPK peparParroPK) {
        this.peparParroPK = peparParroPK;
    }

    public PeparParro(PeparParroPK peparParroPK, String peparDescri) {
        this.peparParroPK = peparParroPK;
        this.peparDescri = peparDescri;
    }

    public PeparParro(String pepaisCodigo, String peprovCodigo, String pecanCodigo, String peparCodigo) {
        this.peparParroPK = new PeparParroPK(pepaisCodigo, peprovCodigo, pecanCodigo, peparCodigo);
    }

    public PeparParroPK getPeparParroPK() {
        return peparParroPK;
    }

    public void setPeparParroPK(PeparParroPK peparParroPK) {
        this.peparParroPK = peparParroPK;
    }

    public String getPeparDescri() {
        return peparDescri;
    }

    public void setPeparDescri(String peparDescri) {
        this.peparDescri = peparDescri;
    }

    @XmlTransient
    public Collection<BealmAlmacn> getBealmAlmacnCollection() {
        return bealmAlmacnCollection;
    }

    public void setBealmAlmacnCollection(Collection<BealmAlmacn> bealmAlmacnCollection) {
        this.bealmAlmacnCollection = bealmAlmacnCollection;
    }

    public PecanCanton getPecanCanton() {
        return pecanCanton;
    }

    public void setPecanCanton(PecanCanton pecanCanton) {
        this.pecanCanton = pecanCanton;
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
        hash += (peparParroPK != null ? peparParroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeparParro)) {
            return false;
        }
        PeparParro other = (PeparParro) object;
        if ((this.peparParroPK == null && other.peparParroPK != null) || (this.peparParroPK != null && !this.peparParroPK.equals(other.peparParroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.PeparParro[ peparParroPK=" + peparParroPK + " ]";
    }
    
}
