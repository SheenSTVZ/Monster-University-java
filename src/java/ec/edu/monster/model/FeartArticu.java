/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhoan
 */
@Entity
@Table(name = "feart_articu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeartArticu.findAll", query = "SELECT f FROM FeartArticu f")
    , @NamedQuery(name = "FeartArticu.findByFeartCodigo2", query = "SELECT f FROM FeartArticu f WHERE f.feartCodigo2 = :feartCodigo2")
    , @NamedQuery(name = "FeartArticu.findByFeartDescri", query = "SELECT f FROM FeartArticu f WHERE f.feartDescri = :feartDescri")
    , @NamedQuery(name = "FeartArticu.findByFeartStock", query = "SELECT f FROM FeartArticu f WHERE f.feartStock = :feartStock")
    , @NamedQuery(name = "FeartArticu.findByFeartPrecom", query = "SELECT f FROM FeartArticu f WHERE f.feartPrecom = :feartPrecom")})
public class FeartArticu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "FEART_CODIGO2")
    private String feartCodigo2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FEART_DESCRI")
    private String feartDescri;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEART_STOCK")
    private BigDecimal feartStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEART_PRECOM")
    private BigDecimal feartPrecom;
    @Lob
    @Size(max = 65535)
    @Column(name = "FEART_OBSER")
    private String feartObser;
    @JoinColumn(name = "FECSO_NUMEROSOLI", referencedColumnName = "FECSO_NUMEROSOLI")
    @ManyToOne(optional = false)
    private FedsoDetsol fecsoNumerosoli;
    @JoinColumn(name = "FECOC_NUMERO2", referencedColumnName = "FECOC_NUMERO2")
    @ManyToOne
    private FedocDeorco fecocNumero2;

    public FeartArticu() {
    }

    public FeartArticu(String feartCodigo2) {
        this.feartCodigo2 = feartCodigo2;
    }

    public FeartArticu(String feartCodigo2, String feartDescri, BigDecimal feartStock, BigDecimal feartPrecom) {
        this.feartCodigo2 = feartCodigo2;
        this.feartDescri = feartDescri;
        this.feartStock = feartStock;
        this.feartPrecom = feartPrecom;
    }

    public String getFeartCodigo2() {
        return feartCodigo2;
    }

    public void setFeartCodigo2(String feartCodigo2) {
        this.feartCodigo2 = feartCodigo2;
    }

    public String getFeartDescri() {
        return feartDescri;
    }

    public void setFeartDescri(String feartDescri) {
        this.feartDescri = feartDescri;
    }

    public BigDecimal getFeartStock() {
        return feartStock;
    }

    public void setFeartStock(BigDecimal feartStock) {
        this.feartStock = feartStock;
    }

    public BigDecimal getFeartPrecom() {
        return feartPrecom;
    }

    public void setFeartPrecom(BigDecimal feartPrecom) {
        this.feartPrecom = feartPrecom;
    }

    public String getFeartObser() {
        return feartObser;
    }

    public void setFeartObser(String feartObser) {
        this.feartObser = feartObser;
    }

    public FedsoDetsol getFecsoNumerosoli() {
        return fecsoNumerosoli;
    }

    public void setFecsoNumerosoli(FedsoDetsol fecsoNumerosoli) {
        this.fecsoNumerosoli = fecsoNumerosoli;
    }

    public FedocDeorco getFecocNumero2() {
        return fecocNumero2;
    }

    public void setFecocNumero2(FedocDeorco fecocNumero2) {
        this.fecocNumero2 = fecocNumero2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feartCodigo2 != null ? feartCodigo2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeartArticu)) {
            return false;
        }
        FeartArticu other = (FeartArticu) object;
        if ((this.feartCodigo2 == null && other.feartCodigo2 != null) || (this.feartCodigo2 != null && !this.feartCodigo2.equals(other.feartCodigo2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.FeartArticu[ feartCodigo2=" + feartCodigo2 + " ]";
    }
    
}
