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
public class XeuxpUsupePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "XEPER_CODIGO")
    private String xeperCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "XEUSU_CODIGO")
    private String xeusuCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "XEUXP_FECASI")
    @Temporal(TemporalType.DATE)
    private Date xeuxpFecasi;

    public XeuxpUsupePK() {
    }

    public XeuxpUsupePK(String xeperCodigo, String xeusuCodigo, Date xeuxpFecasi) {
        this.xeperCodigo = xeperCodigo;
        this.xeusuCodigo = xeusuCodigo;
        this.xeuxpFecasi = xeuxpFecasi;
    }

    public String getXeperCodigo() {
        return xeperCodigo;
    }

    public void setXeperCodigo(String xeperCodigo) {
        this.xeperCodigo = xeperCodigo;
    }

    public String getXeusuCodigo() {
        return xeusuCodigo;
    }

    public void setXeusuCodigo(String xeusuCodigo) {
        this.xeusuCodigo = xeusuCodigo;
    }

    public Date getXeuxpFecasi() {
        return xeuxpFecasi;
    }

    public void setXeuxpFecasi(Date xeuxpFecasi) {
        this.xeuxpFecasi = xeuxpFecasi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeperCodigo != null ? xeperCodigo.hashCode() : 0);
        hash += (xeusuCodigo != null ? xeusuCodigo.hashCode() : 0);
        hash += (xeuxpFecasi != null ? xeuxpFecasi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeuxpUsupePK)) {
            return false;
        }
        XeuxpUsupePK other = (XeuxpUsupePK) object;
        if ((this.xeperCodigo == null && other.xeperCodigo != null) || (this.xeperCodigo != null && !this.xeperCodigo.equals(other.xeperCodigo))) {
            return false;
        }
        if ((this.xeusuCodigo == null && other.xeusuCodigo != null) || (this.xeusuCodigo != null && !this.xeusuCodigo.equals(other.xeusuCodigo))) {
            return false;
        }
        if ((this.xeuxpFecasi == null && other.xeuxpFecasi != null) || (this.xeuxpFecasi != null && !this.xeuxpFecasi.equals(other.xeuxpFecasi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.XeuxpUsupePK[ xeperCodigo=" + xeperCodigo + ", xeusuCodigo=" + xeusuCodigo + ", xeuxpFecasi=" + xeuxpFecasi + " ]";
    }
    
}
