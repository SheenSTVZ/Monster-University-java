/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jhoan
 */
@Embeddable
public class XeoxpOpcpePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "XEPER_CODIGO")
    private String xeperCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "XEOPC_CODIGO")
    private String xeopcCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "XEOXP_FECASI")
    @Temporal(TemporalType.DATE)
    private Date xeoxpFecasi;

    public XeoxpOpcpePK() {
    }

    public XeoxpOpcpePK(String xeperCodigo, String xeopcCodigo, Date xeoxpFecasi) {
        this.xeperCodigo = xeperCodigo;
        this.xeopcCodigo = xeopcCodigo;
        this.xeoxpFecasi = xeoxpFecasi;
    }

    public String getXeperCodigo() {
        return xeperCodigo;
    }

    public void setXeperCodigo(String xeperCodigo) {
        this.xeperCodigo = xeperCodigo;
    }

    public String getXeopcCodigo() {
        return xeopcCodigo;
    }

    public void setXeopcCodigo(String xeopcCodigo) {
        this.xeopcCodigo = xeopcCodigo;
    }

    public Date getXeoxpFecasi() {
        return xeoxpFecasi;
    }

    public void setXeoxpFecasi(Date xeoxpFecasi) {
        this.xeoxpFecasi = xeoxpFecasi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeperCodigo != null ? xeperCodigo.hashCode() : 0);
        hash += (xeopcCodigo != null ? xeopcCodigo.hashCode() : 0);
        hash += (xeoxpFecasi != null ? xeoxpFecasi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeoxpOpcpePK)) {
            return false;
        }
        XeoxpOpcpePK other = (XeoxpOpcpePK) object;
        if ((this.xeperCodigo == null && other.xeperCodigo != null) || (this.xeperCodigo != null && !this.xeperCodigo.equals(other.xeperCodigo))) {
            return false;
        }
        if ((this.xeopcCodigo == null && other.xeopcCodigo != null) || (this.xeopcCodigo != null && !this.xeopcCodigo.equals(other.xeopcCodigo))) {
            return false;
        }
        if ((this.xeoxpFecasi == null && other.xeoxpFecasi != null) || (this.xeoxpFecasi != null && !this.xeoxpFecasi.equals(other.xeoxpFecasi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.XeoxpOpcpePK[ xeperCodigo=" + xeperCodigo + ", xeopcCodigo=" + xeopcCodigo + ", xeoxpFecasi=" + xeoxpFecasi + " ]";
    }
    
}
