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
import javax.persistence.JoinColumn;
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
@Table(name = "beinv_inven")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BeinvInven.findAll", query = "SELECT b FROM BeinvInven b")
    , @NamedQuery(name = "BeinvInven.findByBeinvCodigo", query = "SELECT b FROM BeinvInven b WHERE b.beinvCodigo = :beinvCodigo")
    , @NamedQuery(name = "BeinvInven.findByBeinvDescri", query = "SELECT b FROM BeinvInven b WHERE b.beinvDescri = :beinvDescri")})
public class BeinvInven implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "BEINV_CODIGO")
    private String beinvCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEINV_DESCRI")
    private String beinvDescri;
    @JoinColumn(name = "BEALM_CODIGO", referencedColumnName = "BEALM_CODIGO")
    @ManyToOne(optional = false)
    private BealmAlmacn bealmCodigo;
    @OneToMany(mappedBy = "beinvCodigo")
    private Collection<BeartArticu> beartArticuCollection;

    public BeinvInven() {
    }

    public BeinvInven(String beinvCodigo) {
        this.beinvCodigo = beinvCodigo;
    }

    public BeinvInven(String beinvCodigo, String beinvDescri) {
        this.beinvCodigo = beinvCodigo;
        this.beinvDescri = beinvDescri;
    }

    public String getBeinvCodigo() {
        return beinvCodigo;
    }

    public void setBeinvCodigo(String beinvCodigo) {
        this.beinvCodigo = beinvCodigo;
    }

    public String getBeinvDescri() {
        return beinvDescri;
    }

    public void setBeinvDescri(String beinvDescri) {
        this.beinvDescri = beinvDescri;
    }

    public BealmAlmacn getBealmCodigo() {
        return bealmCodigo;
    }

    public void setBealmCodigo(BealmAlmacn bealmCodigo) {
        this.bealmCodigo = bealmCodigo;
    }

    @XmlTransient
    public Collection<BeartArticu> getBeartArticuCollection() {
        return beartArticuCollection;
    }

    public void setBeartArticuCollection(Collection<BeartArticu> beartArticuCollection) {
        this.beartArticuCollection = beartArticuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (beinvCodigo != null ? beinvCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeinvInven)) {
            return false;
        }
        BeinvInven other = (BeinvInven) object;
        if ((this.beinvCodigo == null && other.beinvCodigo != null) || (this.beinvCodigo != null && !this.beinvCodigo.equals(other.beinvCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.BeinvInven[ beinvCodigo=" + beinvCodigo + " ]";
    }
    
}
