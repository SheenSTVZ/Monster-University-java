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
import javax.persistence.Lob;
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
@Table(name = "beart_articu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BeartArticu.findAll", query = "SELECT b FROM BeartArticu b")
    , @NamedQuery(name = "BeartArticu.findByFeartCodigo", query = "SELECT b FROM BeartArticu b WHERE b.feartCodigo = :feartCodigo")
    , @NamedQuery(name = "BeartArticu.findByFeartDescri", query = "SELECT b FROM BeartArticu b WHERE b.feartDescri = :feartDescri")
    , @NamedQuery(name = "BeartArticu.findByFeartStock", query = "SELECT b FROM BeartArticu b WHERE b.feartStock = :feartStock")
    , @NamedQuery(name = "BeartArticu.findByFeartPrecom", query = "SELECT b FROM BeartArticu b WHERE b.feartPrecom = :feartPrecom")})
public class BeartArticu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "FEART_CODIGO")
    private String feartCodigo;
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
    @OneToMany(mappedBy = "feartCodigo")
    private Collection<BesdaSalalm> besdaSalalmCollection;
    @JoinColumn(name = "BEINV_CODIGO", referencedColumnName = "BEINV_CODIGO")
    @ManyToOne
    private BeinvInven beinvCodigo;
    @JoinColumn(name = "BEEDA_NUMERO2", referencedColumnName = "BEEDA_NUMERO2")
    @ManyToOne(optional = false)
    private BeedaEntalm beedaNumero2;

    public BeartArticu() {
    }

    public BeartArticu(String feartCodigo) {
        this.feartCodigo = feartCodigo;
    }

    public BeartArticu(String feartCodigo, String feartDescri, BigDecimal feartStock, BigDecimal feartPrecom) {
        this.feartCodigo = feartCodigo;
        this.feartDescri = feartDescri;
        this.feartStock = feartStock;
        this.feartPrecom = feartPrecom;
    }

    public String getFeartCodigo() {
        return feartCodigo;
    }

    public void setFeartCodigo(String feartCodigo) {
        this.feartCodigo = feartCodigo;
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

    @XmlTransient
    public Collection<BesdaSalalm> getBesdaSalalmCollection() {
        return besdaSalalmCollection;
    }

    public void setBesdaSalalmCollection(Collection<BesdaSalalm> besdaSalalmCollection) {
        this.besdaSalalmCollection = besdaSalalmCollection;
    }

    public BeinvInven getBeinvCodigo() {
        return beinvCodigo;
    }

    public void setBeinvCodigo(BeinvInven beinvCodigo) {
        this.beinvCodigo = beinvCodigo;
    }

    public BeedaEntalm getBeedaNumero2() {
        return beedaNumero2;
    }

    public void setBeedaNumero2(BeedaEntalm beedaNumero2) {
        this.beedaNumero2 = beedaNumero2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feartCodigo != null ? feartCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeartArticu)) {
            return false;
        }
        BeartArticu other = (BeartArticu) object;
        if ((this.feartCodigo == null && other.feartCodigo != null) || (this.feartCodigo != null && !this.feartCodigo.equals(other.feartCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.BeartArticu[ feartCodigo=" + feartCodigo + " ]";
    }
    
}
