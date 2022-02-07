/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhoan
 */
@Entity
@Table(name = "xeoxp_opcpe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XeoxpOpcpe.findAll", query = "SELECT x FROM XeoxpOpcpe x")
    , @NamedQuery(name = "XeoxpOpcpe.findByXeperCodigo", query = "SELECT x FROM XeoxpOpcpe x WHERE x.xeoxpOpcpePK.xeperCodigo = :xeperCodigo")
    , @NamedQuery(name = "XeoxpOpcpe.findByXeopcCodigo", query = "SELECT x FROM XeoxpOpcpe x WHERE x.xeoxpOpcpePK.xeopcCodigo = :xeopcCodigo")
    , @NamedQuery(name = "XeoxpOpcpe.findByXeoxpFecasi", query = "SELECT x FROM XeoxpOpcpe x WHERE x.xeoxpOpcpePK.xeoxpFecasi = :xeoxpFecasi")
    , @NamedQuery(name = "XeoxpOpcpe.findByXeoxpFecret", query = "SELECT x FROM XeoxpOpcpe x WHERE x.xeoxpFecret = :xeoxpFecret")})
public class XeoxpOpcpe implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected XeoxpOpcpePK xeoxpOpcpePK;
    @Column(name = "XEOXP_FECRET")
    @Temporal(TemporalType.DATE)
    private Date xeoxpFecret;
    @JoinColumn(name = "XEOPC_CODIGO", referencedColumnName = "XEOPC_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private XeopcOpcio xeopcOpcio;
    @JoinColumn(name = "XEPER_CODIGO", referencedColumnName = "XEPER_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private XeperPerfi xeperPerfi;

    public XeoxpOpcpe() {
    }

    public XeoxpOpcpe(XeoxpOpcpePK xeoxpOpcpePK) {
        this.xeoxpOpcpePK = xeoxpOpcpePK;
    }

    public XeoxpOpcpe(String xeperCodigo, String xeopcCodigo, Date xeoxpFecasi) {
        this.xeoxpOpcpePK = new XeoxpOpcpePK(xeperCodigo, xeopcCodigo, xeoxpFecasi);
    }

    public XeoxpOpcpePK getXeoxpOpcpePK() {
        return xeoxpOpcpePK;
    }

    public void setXeoxpOpcpePK(XeoxpOpcpePK xeoxpOpcpePK) {
        this.xeoxpOpcpePK = xeoxpOpcpePK;
    }

    public Date getXeoxpFecret() {
        return xeoxpFecret;
    }

    public void setXeoxpFecret(Date xeoxpFecret) {
        this.xeoxpFecret = xeoxpFecret;
    }

    public XeopcOpcio getXeopcOpcio() {
        return xeopcOpcio;
    }

    public void setXeopcOpcio(XeopcOpcio xeopcOpcio) {
        this.xeopcOpcio = xeopcOpcio;
    }

    public XeperPerfi getXeperPerfi() {
        return xeperPerfi;
    }

    public void setXeperPerfi(XeperPerfi xeperPerfi) {
        this.xeperPerfi = xeperPerfi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeoxpOpcpePK != null ? xeoxpOpcpePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeoxpOpcpe)) {
            return false;
        }
        XeoxpOpcpe other = (XeoxpOpcpe) object;
        if ((this.xeoxpOpcpePK == null && other.xeoxpOpcpePK != null) || (this.xeoxpOpcpePK != null && !this.xeoxpOpcpePK.equals(other.xeoxpOpcpePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.XeoxpOpcpe[ xeoxpOpcpePK=" + xeoxpOpcpePK + " ]";
    }
    
}
