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
@Table(name = "pepais_pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PepaisPais.findAll", query = "SELECT p FROM PepaisPais p")
    , @NamedQuery(name = "PepaisPais.findByPepaisCodigo", query = "SELECT p FROM PepaisPais p WHERE p.pepaisCodigo = :pepaisCodigo")
    , @NamedQuery(name = "PepaisPais.findByPepaisDescri", query = "SELECT p FROM PepaisPais p WHERE p.pepaisDescri = :pepaisDescri")})
public class PepaisPais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "PEPAIS_CODIGO")
    private String pepaisCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PEPAIS_DESCRI")
    private String pepaisDescri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pepaisPais")
    private Collection<PeprovProvin> peprovProvinCollection;

    public PepaisPais() {
    }

    public PepaisPais(String pepaisCodigo) {
        this.pepaisCodigo = pepaisCodigo;
    }

    public PepaisPais(String pepaisCodigo, String pepaisDescri) {
        this.pepaisCodigo = pepaisCodigo;
        this.pepaisDescri = pepaisDescri;
    }

    public String getPepaisCodigo() {
        return pepaisCodigo;
    }

    public void setPepaisCodigo(String pepaisCodigo) {
        this.pepaisCodigo = pepaisCodigo;
    }

    public String getPepaisDescri() {
        return pepaisDescri;
    }

    public void setPepaisDescri(String pepaisDescri) {
        this.pepaisDescri = pepaisDescri;
    }

    @XmlTransient
    public Collection<PeprovProvin> getPeprovProvinCollection() {
        return peprovProvinCollection;
    }

    public void setPeprovProvinCollection(Collection<PeprovProvin> peprovProvinCollection) {
        this.peprovProvinCollection = peprovProvinCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pepaisCodigo != null ? pepaisCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PepaisPais)) {
            return false;
        }
        PepaisPais other = (PepaisPais) object;
        if ((this.pepaisCodigo == null && other.pepaisCodigo != null) || (this.pepaisCodigo != null && !this.pepaisCodigo.equals(other.pepaisCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.PepaisPais[ pepaisCodigo=" + pepaisCodigo + " ]";
    }
    
}
