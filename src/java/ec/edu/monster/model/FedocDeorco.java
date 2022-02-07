/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "fedoc_deorco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FedocDeorco.findAll", query = "SELECT f FROM FedocDeorco f")
    , @NamedQuery(name = "FedocDeorco.findByFecocNumero2", query = "SELECT f FROM FedocDeorco f WHERE f.fecocNumero2 = :fecocNumero2")
    , @NamedQuery(name = "FedocDeorco.findByFedocCanti", query = "SELECT f FROM FedocDeorco f WHERE f.fedocCanti = :fedocCanti")
    , @NamedQuery(name = "FedocDeorco.findByFedocCoscom", query = "SELECT f FROM FedocDeorco f WHERE f.fedocCoscom = :fedocCoscom")
    , @NamedQuery(name = "FedocDeorco.findByFedocSubtot", query = "SELECT f FROM FedocDeorco f WHERE f.fedocSubtot = :fedocSubtot")})
public class FedocDeorco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "FECOC_NUMERO2")
    private String fecocNumero2;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEDOC_CANTI")
    private BigDecimal fedocCanti;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEDOC_COSCOM")
    private BigDecimal fedocCoscom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEDOC_SUBTOT")
    private BigDecimal fedocSubtot;
    @OneToMany(mappedBy = "fecocNumero2")
    private Collection<FeartArticu> feartArticuCollection;
    @JoinColumn(name = "FECOC_NUMERO2", referencedColumnName = "FECOC_NUMERO2", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private FecocCaor fecocCaor;

    public FedocDeorco() {
    }

    public FedocDeorco(String fecocNumero2) {
        this.fecocNumero2 = fecocNumero2;
    }

    public FedocDeorco(String fecocNumero2, BigDecimal fedocCanti, BigDecimal fedocCoscom, BigDecimal fedocSubtot) {
        this.fecocNumero2 = fecocNumero2;
        this.fedocCanti = fedocCanti;
        this.fedocCoscom = fedocCoscom;
        this.fedocSubtot = fedocSubtot;
    }

    public String getFecocNumero2() {
        return fecocNumero2;
    }

    public void setFecocNumero2(String fecocNumero2) {
        this.fecocNumero2 = fecocNumero2;
    }

    public BigDecimal getFedocCanti() {
        return fedocCanti;
    }

    public void setFedocCanti(BigDecimal fedocCanti) {
        this.fedocCanti = fedocCanti;
    }

    public BigDecimal getFedocCoscom() {
        return fedocCoscom;
    }

    public void setFedocCoscom(BigDecimal fedocCoscom) {
        this.fedocCoscom = fedocCoscom;
    }

    public BigDecimal getFedocSubtot() {
        return fedocSubtot;
    }

    public void setFedocSubtot(BigDecimal fedocSubtot) {
        this.fedocSubtot = fedocSubtot;
    }

    @XmlTransient
    public Collection<FeartArticu> getFeartArticuCollection() {
        return feartArticuCollection;
    }

    public void setFeartArticuCollection(Collection<FeartArticu> feartArticuCollection) {
        this.feartArticuCollection = feartArticuCollection;
    }

    public FecocCaor getFecocCaor() {
        return fecocCaor;
    }

    public void setFecocCaor(FecocCaor fecocCaor) {
        this.fecocCaor = fecocCaor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecocNumero2 != null ? fecocNumero2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FedocDeorco)) {
            return false;
        }
        FedocDeorco other = (FedocDeorco) object;
        if ((this.fecocNumero2 == null && other.fecocNumero2 != null) || (this.fecocNumero2 != null && !this.fecocNumero2.equals(other.fecocNumero2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.FedocDeorco[ fecocNumero2=" + fecocNumero2 + " ]";
    }
    
}
