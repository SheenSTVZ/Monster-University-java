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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "besda_salalm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BesdaSalalm.findAll", query = "SELECT b FROM BesdaSalalm b")
    , @NamedQuery(name = "BesdaSalalm.findByBesdaNumero", query = "SELECT b FROM BesdaSalalm b WHERE b.besdaNumero = :besdaNumero")
    , @NamedQuery(name = "BesdaSalalm.findByBesdaResponsable", query = "SELECT b FROM BesdaSalalm b WHERE b.besdaResponsable = :besdaResponsable")
    , @NamedQuery(name = "BesdaSalalm.findByBesdaFechasalida", query = "SELECT b FROM BesdaSalalm b WHERE b.besdaFechasalida = :besdaFechasalida")
    , @NamedQuery(name = "BesdaSalalm.findByBesdaFechaentrega", query = "SELECT b FROM BesdaSalalm b WHERE b.besdaFechaentrega = :besdaFechaentrega")
    , @NamedQuery(name = "BesdaSalalm.findByBesdaCantidade", query = "SELECT b FROM BesdaSalalm b WHERE b.besdaCantidade = :besdaCantidade")})
public class BesdaSalalm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BESDA_NUMERO")
    private Short besdaNumero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "BESDA_RESPONSABLE")
    private String besdaResponsable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BESDA_FECHASALIDA")
    @Temporal(TemporalType.DATE)
    private Date besdaFechasalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BESDA_FECHAENTREGA")
    @Temporal(TemporalType.DATE)
    private Date besdaFechaentrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "BESDA_CANTIDADE")
    private BigDecimal besdaCantidade;
    @JoinColumn(name = "FEART_CODIGO", referencedColumnName = "FEART_CODIGO")
    @ManyToOne
    private BeartArticu feartCodigo;

    public BesdaSalalm() {
    }

    public BesdaSalalm(Short besdaNumero) {
        this.besdaNumero = besdaNumero;
    }

    public BesdaSalalm(Short besdaNumero, String besdaResponsable, Date besdaFechasalida, Date besdaFechaentrega, BigDecimal besdaCantidade) {
        this.besdaNumero = besdaNumero;
        this.besdaResponsable = besdaResponsable;
        this.besdaFechasalida = besdaFechasalida;
        this.besdaFechaentrega = besdaFechaentrega;
        this.besdaCantidade = besdaCantidade;
    }

    public Short getBesdaNumero() {
        return besdaNumero;
    }

    public void setBesdaNumero(Short besdaNumero) {
        this.besdaNumero = besdaNumero;
    }

    public String getBesdaResponsable() {
        return besdaResponsable;
    }

    public void setBesdaResponsable(String besdaResponsable) {
        this.besdaResponsable = besdaResponsable;
    }

    public Date getBesdaFechasalida() {
        return besdaFechasalida;
    }

    public void setBesdaFechasalida(Date besdaFechasalida) {
        this.besdaFechasalida = besdaFechasalida;
    }

    public Date getBesdaFechaentrega() {
        return besdaFechaentrega;
    }

    public void setBesdaFechaentrega(Date besdaFechaentrega) {
        this.besdaFechaentrega = besdaFechaentrega;
    }

    public BigDecimal getBesdaCantidade() {
        return besdaCantidade;
    }

    public void setBesdaCantidade(BigDecimal besdaCantidade) {
        this.besdaCantidade = besdaCantidade;
    }

    public BeartArticu getFeartCodigo() {
        return feartCodigo;
    }

    public void setFeartCodigo(BeartArticu feartCodigo) {
        this.feartCodigo = feartCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (besdaNumero != null ? besdaNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BesdaSalalm)) {
            return false;
        }
        BesdaSalalm other = (BesdaSalalm) object;
        if ((this.besdaNumero == null && other.besdaNumero != null) || (this.besdaNumero != null && !this.besdaNumero.equals(other.besdaNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.BesdaSalalm[ besdaNumero=" + besdaNumero + " ]";
    }
    
}
