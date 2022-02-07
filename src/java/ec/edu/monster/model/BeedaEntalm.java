/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhoan
 */
@Entity
@Table(name = "beeda_entalm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BeedaEntalm.findAll", query = "SELECT b FROM BeedaEntalm b")
    , @NamedQuery(name = "BeedaEntalm.findByBeedaNumero2", query = "SELECT b FROM BeedaEntalm b WHERE b.beedaNumero2 = :beedaNumero2")
    , @NamedQuery(name = "BeedaEntalm.findByBeedaFecha", query = "SELECT b FROM BeedaEntalm b WHERE b.beedaFecha = :beedaFecha")
    , @NamedQuery(name = "BeedaEntalm.findByBeedaNumerofactura", query = "SELECT b FROM BeedaEntalm b WHERE b.beedaNumerofactura = :beedaNumerofactura")
    , @NamedQuery(name = "BeedaEntalm.findByBeedaTotalbienes", query = "SELECT b FROM BeedaEntalm b WHERE b.beedaTotalbienes = :beedaTotalbienes")
    , @NamedQuery(name = "BeedaEntalm.findByBeedaValortotal", query = "SELECT b FROM BeedaEntalm b WHERE b.beedaValortotal = :beedaValortotal")})
public class BeedaEntalm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BEEDA_NUMERO2")
    private Short beedaNumero2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BEEDA_FECHA")
    @Temporal(TemporalType.DATE)
    private Date beedaFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BEEDA_NUMEROFACTURA")
    private short beedaNumerofactura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "BEEDA_TOTALBIENES")
    private BigDecimal beedaTotalbienes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BEEDA_VALORTOTAL")
    private BigDecimal beedaValortotal;
    @JoinColumn(name = "BEPRV_CODIGO2", referencedColumnName = "BEPRV_CODIGO2")
    @ManyToOne(optional = false)
    private BeprvProv beprvCodigo2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beedaNumero2")
    private Collection<BeartArticu> beartArticuCollection;

    public BeedaEntalm() {
    }

    public BeedaEntalm(Short beedaNumero2) {
        this.beedaNumero2 = beedaNumero2;
    }

    public BeedaEntalm(Short beedaNumero2, Date beedaFecha, short beedaNumerofactura, BigDecimal beedaTotalbienes, BigDecimal beedaValortotal) {
        this.beedaNumero2 = beedaNumero2;
        this.beedaFecha = beedaFecha;
        this.beedaNumerofactura = beedaNumerofactura;
        this.beedaTotalbienes = beedaTotalbienes;
        this.beedaValortotal = beedaValortotal;
    }

    public Short getBeedaNumero2() {
        return beedaNumero2;
    }

    public void setBeedaNumero2(Short beedaNumero2) {
        this.beedaNumero2 = beedaNumero2;
    }

    public Date getBeedaFecha() {
        return beedaFecha;
    }

    public void setBeedaFecha(Date beedaFecha) {
        this.beedaFecha = beedaFecha;
    }

    public short getBeedaNumerofactura() {
        return beedaNumerofactura;
    }

    public void setBeedaNumerofactura(short beedaNumerofactura) {
        this.beedaNumerofactura = beedaNumerofactura;
    }

    public BigDecimal getBeedaTotalbienes() {
        return beedaTotalbienes;
    }

    public void setBeedaTotalbienes(BigDecimal beedaTotalbienes) {
        this.beedaTotalbienes = beedaTotalbienes;
    }

    public BigDecimal getBeedaValortotal() {
        return beedaValortotal;
    }

    public void setBeedaValortotal(BigDecimal beedaValortotal) {
        this.beedaValortotal = beedaValortotal;
    }

    public BeprvProv getBeprvCodigo2() {
        return beprvCodigo2;
    }

    public void setBeprvCodigo2(BeprvProv beprvCodigo2) {
        this.beprvCodigo2 = beprvCodigo2;
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
        hash += (beedaNumero2 != null ? beedaNumero2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeedaEntalm)) {
            return false;
        }
        BeedaEntalm other = (BeedaEntalm) object;
        if ((this.beedaNumero2 == null && other.beedaNumero2 != null) || (this.beedaNumero2 != null && !this.beedaNumero2.equals(other.beedaNumero2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.BeedaEntalm[ beedaNumero2=" + beedaNumero2 + " ]";
    }
    
}
