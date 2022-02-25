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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "xeven_ventan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XevenVentan.findAll", query = "SELECT x FROM XevenVentan x")
    , @NamedQuery(name = "XevenVentan.findByXevenCodigo", query = "SELECT x FROM XevenVentan x WHERE x.xevenCodigo = :xevenCodigo")
    , @NamedQuery(name = "XevenVentan.findByXevenDescri", query = "SELECT x FROM XevenVentan x WHERE x.xevenDescri = :xevenDescri")})
public class XevenVentan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "XEVEN_CODIGO")
    private String xevenCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "XEVEN_DESCRI")
    private String xevenDescri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xevenCodigo")
    private Collection<XeopcOpcio> xeopcOpcioCollection;

    public XevenVentan() {
    }

    public XevenVentan(String xevenCodigo) {
        this.xevenCodigo = xevenCodigo;
    }

    public XevenVentan(String xevenCodigo, String xevenDescri) {
        this.xevenCodigo = xevenCodigo;
        this.xevenDescri = xevenDescri;
    }

    public String getXevenCodigo() {
        return xevenCodigo;
    }

    public void setXevenCodigo(String xevenCodigo) {
        this.xevenCodigo = xevenCodigo;
    }

    public String getXevenDescri() {
        return xevenDescri;
    }

    public void setXevenDescri(String xevenDescri) {
        this.xevenDescri = xevenDescri;
    }

    @XmlTransient
    public Collection<XeopcOpcio> getXeopcOpcioCollection() {
        return xeopcOpcioCollection;
    }

    public void setXeopcOpcioCollection(Collection<XeopcOpcio> xeopcOpcioCollection) {
        this.xeopcOpcioCollection = xeopcOpcioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xevenCodigo != null ? xevenCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XevenVentan)) {
            return false;
        }
        XevenVentan other = (XevenVentan) object;
        if ((this.xevenCodigo == null && other.xevenCodigo != null) || (this.xevenCodigo != null && !this.xevenCodigo.equals(other.xevenCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.XevenVentan[ xevenCodigo=" + xevenCodigo + " ]";
    }
    
}
