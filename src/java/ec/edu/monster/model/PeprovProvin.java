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
@Table(name = "peprov_provin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeprovProvin.findAll", query = "SELECT p FROM PeprovProvin p")
    , @NamedQuery(name = "PeprovProvin.findByPepaisCodigo", query = "SELECT p FROM PeprovProvin p WHERE p.peprovProvinPK.pepaisCodigo = :pepaisCodigo")
    , @NamedQuery(name = "PeprovProvin.findByPeprovCodigo", query = "SELECT p FROM PeprovProvin p WHERE p.peprovProvinPK.peprovCodigo = :peprovCodigo")
    , @NamedQuery(name = "PeprovProvin.findByPeprovDescri", query = "SELECT p FROM PeprovProvin p WHERE p.peprovDescri = :peprovDescri")})
public class PeprovProvin implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeprovProvinPK peprovProvinPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PEPROV_DESCRI")
    private String peprovDescri;
    @JoinColumn(name = "PEPAIS_CODIGO", referencedColumnName = "PEPAIS_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PepaisPais pepaisPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peprovProvin")
    private Collection<PecanCanton> pecanCantonCollection;

    public PeprovProvin() {
    }

    public PeprovProvin(PeprovProvinPK peprovProvinPK) {
        this.peprovProvinPK = peprovProvinPK;
    }

    public PeprovProvin(PeprovProvinPK peprovProvinPK, String peprovDescri) {
        this.peprovProvinPK = peprovProvinPK;
        this.peprovDescri = peprovDescri;
    }

    public PeprovProvin(String pepaisCodigo, String peprovCodigo) {
        this.peprovProvinPK = new PeprovProvinPK(pepaisCodigo, peprovCodigo);
    }

    public PeprovProvinPK getPeprovProvinPK() {
        return peprovProvinPK;
    }

    public void setPeprovProvinPK(PeprovProvinPK peprovProvinPK) {
        this.peprovProvinPK = peprovProvinPK;
    }

    public String getPeprovDescri() {
        return peprovDescri;
    }

    public void setPeprovDescri(String peprovDescri) {
        this.peprovDescri = peprovDescri;
    }

    public PepaisPais getPepaisPais() {
        return pepaisPais;
    }

    public void setPepaisPais(PepaisPais pepaisPais) {
        this.pepaisPais = pepaisPais;
    }

    @XmlTransient
    public Collection<PecanCanton> getPecanCantonCollection() {
        return pecanCantonCollection;
    }

    public void setPecanCantonCollection(Collection<PecanCanton> pecanCantonCollection) {
        this.pecanCantonCollection = pecanCantonCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peprovProvinPK != null ? peprovProvinPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeprovProvin)) {
            return false;
        }
        PeprovProvin other = (PeprovProvin) object;
        if ((this.peprovProvinPK == null && other.peprovProvinPK != null) || (this.peprovProvinPK != null && !this.peprovProvinPK.equals(other.peprovProvinPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.PeprovProvin[ peprovProvinPK=" + peprovProvinPK + " ]";
    }
    
}
