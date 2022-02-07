/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhoan
 */
@Entity
@Table(name = "fecoc_caor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FecocCaor.findAll", query = "SELECT f FROM FecocCaor f")
    , @NamedQuery(name = "FecocCaor.findByFecocNumero2", query = "SELECT f FROM FecocCaor f WHERE f.fecocNumero2 = :fecocNumero2")
    , @NamedQuery(name = "FecocCaor.findByFecocFecha", query = "SELECT f FROM FecocCaor f WHERE f.fecocFecha = :fecocFecha")
    , @NamedQuery(name = "FecocCaor.findByFecocTotal", query = "SELECT f FROM FecocCaor f WHERE f.fecocTotal = :fecocTotal")
    , @NamedQuery(name = "FecocCaor.findByFecocIva", query = "SELECT f FROM FecocCaor f WHERE f.fecocIva = :fecocIva")
    , @NamedQuery(name = "FecocCaor.findByFecocDescu", query = "SELECT f FROM FecocCaor f WHERE f.fecocDescu = :fecocDescu")
    , @NamedQuery(name = "FecocCaor.findByFecocTotpag", query = "SELECT f FROM FecocCaor f WHERE f.fecocTotpag = :fecocTotpag")})
public class FecocCaor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "FECOC_NUMERO2")
    private String fecocNumero2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECOC_FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecocFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECOC_TOTAL")
    private BigDecimal fecocTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECOC_IVA")
    private BigDecimal fecocIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECOC_DESCU")
    private BigDecimal fecocDescu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECOC_TOTPAG")
    private BigDecimal fecocTotpag;
    @JoinColumn(name = "FEPRV_CODIGO3", referencedColumnName = "FEPRV_CODIGO3")
    @ManyToOne(optional = false)
    private FeprvProv feprvCodigo3;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fecocCaor")
    private FedocDeorco fedocDeorco;

    public FecocCaor() {
    }

    public FecocCaor(String fecocNumero2) {
        this.fecocNumero2 = fecocNumero2;
    }

    public FecocCaor(String fecocNumero2, Date fecocFecha, BigDecimal fecocTotal, BigDecimal fecocIva, BigDecimal fecocDescu, BigDecimal fecocTotpag) {
        this.fecocNumero2 = fecocNumero2;
        this.fecocFecha = fecocFecha;
        this.fecocTotal = fecocTotal;
        this.fecocIva = fecocIva;
        this.fecocDescu = fecocDescu;
        this.fecocTotpag = fecocTotpag;
    }

    public String getFecocNumero2() {
        return fecocNumero2;
    }

    public void setFecocNumero2(String fecocNumero2) {
        this.fecocNumero2 = fecocNumero2;
    }

    public Date getFecocFecha() {
        return fecocFecha;
    }

    public void setFecocFecha(Date fecocFecha) {
        this.fecocFecha = fecocFecha;
    }

    public BigDecimal getFecocTotal() {
        return fecocTotal;
    }

    public void setFecocTotal(BigDecimal fecocTotal) {
        this.fecocTotal = fecocTotal;
    }

    public BigDecimal getFecocIva() {
        return fecocIva;
    }

    public void setFecocIva(BigDecimal fecocIva) {
        this.fecocIva = fecocIva;
    }

    public BigDecimal getFecocDescu() {
        return fecocDescu;
    }

    public void setFecocDescu(BigDecimal fecocDescu) {
        this.fecocDescu = fecocDescu;
    }

    public BigDecimal getFecocTotpag() {
        return fecocTotpag;
    }

    public void setFecocTotpag(BigDecimal fecocTotpag) {
        this.fecocTotpag = fecocTotpag;
    }

    public FeprvProv getFeprvCodigo3() {
        return feprvCodigo3;
    }

    public void setFeprvCodigo3(FeprvProv feprvCodigo3) {
        this.feprvCodigo3 = feprvCodigo3;
    }

    public FedocDeorco getFedocDeorco() {
        return fedocDeorco;
    }

    public void setFedocDeorco(FedocDeorco fedocDeorco) {
        this.fedocDeorco = fedocDeorco;
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
        if (!(object instanceof FecocCaor)) {
            return false;
        }
        FecocCaor other = (FecocCaor) object;
        if ((this.fecocNumero2 == null && other.fecocNumero2 != null) || (this.fecocNumero2 != null && !this.fecocNumero2.equals(other.fecocNumero2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.FecocCaor[ fecocNumero2=" + fecocNumero2 + " ]";
    }
    
}
