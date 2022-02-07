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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "bealm_almacn")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BealmAlmacn.findAll", query = "SELECT b FROM BealmAlmacn b")
    , @NamedQuery(name = "BealmAlmacn.findByBealmCodigo", query = "SELECT b FROM BealmAlmacn b WHERE b.bealmCodigo = :bealmCodigo")
    , @NamedQuery(name = "BealmAlmacn.findByBealmNombre", query = "SELECT b FROM BealmAlmacn b WHERE b.bealmNombre = :bealmNombre")
    , @NamedQuery(name = "BealmAlmacn.findByBealmDescri", query = "SELECT b FROM BealmAlmacn b WHERE b.bealmDescri = :bealmDescri")})
public class BealmAlmacn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "BEALM_CODIGO")
    private String bealmCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEALM_NOMBRE")
    private String bealmNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEALM_DESCRI")
    private String bealmDescri;
    @JoinColumns({
        @JoinColumn(name = "PEPAIS_CODIGO", referencedColumnName = "PEPAIS_CODIGO")
        , @JoinColumn(name = "PEPROV_CODIGO", referencedColumnName = "PEPROV_CODIGO")
        , @JoinColumn(name = "PECAN_CODIGO", referencedColumnName = "PECAN_CODIGO")
        , @JoinColumn(name = "PEPAR_CODIGO", referencedColumnName = "PEPAR_CODIGO")})
    @ManyToOne(optional = false)
    private PeparParro peparParro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bealmCodigo")
    private Collection<BeinvInven> beinvInvenCollection;
    @OneToMany(mappedBy = "bealmCodigo")
    private Collection<FeempEmple> feempEmpleCollection;

    public BealmAlmacn() {
    }

    public BealmAlmacn(String bealmCodigo) {
        this.bealmCodigo = bealmCodigo;
    }

    public BealmAlmacn(String bealmCodigo, String bealmNombre, String bealmDescri) {
        this.bealmCodigo = bealmCodigo;
        this.bealmNombre = bealmNombre;
        this.bealmDescri = bealmDescri;
    }

    public String getBealmCodigo() {
        return bealmCodigo;
    }

    public void setBealmCodigo(String bealmCodigo) {
        this.bealmCodigo = bealmCodigo;
    }

    public String getBealmNombre() {
        return bealmNombre;
    }

    public void setBealmNombre(String bealmNombre) {
        this.bealmNombre = bealmNombre;
    }

    public String getBealmDescri() {
        return bealmDescri;
    }

    public void setBealmDescri(String bealmDescri) {
        this.bealmDescri = bealmDescri;
    }

    public PeparParro getPeparParro() {
        return peparParro;
    }

    public void setPeparParro(PeparParro peparParro) {
        this.peparParro = peparParro;
    }

    @XmlTransient
    public Collection<BeinvInven> getBeinvInvenCollection() {
        return beinvInvenCollection;
    }

    public void setBeinvInvenCollection(Collection<BeinvInven> beinvInvenCollection) {
        this.beinvInvenCollection = beinvInvenCollection;
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
        hash += (bealmCodigo != null ? bealmCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BealmAlmacn)) {
            return false;
        }
        BealmAlmacn other = (BealmAlmacn) object;
        if ((this.bealmCodigo == null && other.bealmCodigo != null) || (this.bealmCodigo != null && !this.bealmCodigo.equals(other.bealmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.BealmAlmacn[ bealmCodigo=" + bealmCodigo + " ]";
    }
    
}
