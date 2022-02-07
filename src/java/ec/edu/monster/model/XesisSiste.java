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
@Table(name = "xesis_siste")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XesisSiste.findAll", query = "SELECT x FROM XesisSiste x")
    , @NamedQuery(name = "XesisSiste.findByXesisCodigo", query = "SELECT x FROM XesisSiste x WHERE x.xesisCodigo = :xesisCodigo")
    , @NamedQuery(name = "XesisSiste.findByXesisDescri", query = "SELECT x FROM XesisSiste x WHERE x.xesisDescri = :xesisDescri")})
public class XesisSiste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "XESIS_CODIGO")
    private String xesisCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "XESIS_DESCRI")
    private String xesisDescri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xesisCodigo")
    private Collection<XeopcOpcio> xeopcOpcioCollection;

    public XesisSiste() {
    }

    public XesisSiste(String xesisCodigo) {
        this.xesisCodigo = xesisCodigo;
    }

    public XesisSiste(String xesisCodigo, String xesisDescri) {
        this.xesisCodigo = xesisCodigo;
        this.xesisDescri = xesisDescri;
    }

    public String getXesisCodigo() {
        return xesisCodigo;
    }

    public void setXesisCodigo(String xesisCodigo) {
        this.xesisCodigo = xesisCodigo;
    }

    public String getXesisDescri() {
        return xesisDescri;
    }

    public void setXesisDescri(String xesisDescri) {
        this.xesisDescri = xesisDescri;
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
        hash += (xesisCodigo != null ? xesisCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XesisSiste)) {
            return false;
        }
        XesisSiste other = (XesisSiste) object;
        if ((this.xesisCodigo == null && other.xesisCodigo != null) || (this.xesisCodigo != null && !this.xesisCodigo.equals(other.xesisCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.XesisSiste[ xesisCodigo=" + xesisCodigo + " ]";
    }
    
}
