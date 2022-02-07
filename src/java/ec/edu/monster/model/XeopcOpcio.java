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
import javax.persistence.JoinColumn;
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
@Table(name = "xeopc_opcio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XeopcOpcio.findAll", query = "SELECT x FROM XeopcOpcio x")
    , @NamedQuery(name = "XeopcOpcio.findByXeopcCodigo", query = "SELECT x FROM XeopcOpcio x WHERE x.xeopcCodigo = :xeopcCodigo")
    , @NamedQuery(name = "XeopcOpcio.findByXeopcDescri", query = "SELECT x FROM XeopcOpcio x WHERE x.xeopcDescri = :xeopcDescri")})
public class XeopcOpcio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "XEOPC_CODIGO")
    private String xeopcCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEOPC_DESCRI")
    private String xeopcDescri;
    @JoinColumn(name = "XESIS_CODIGO", referencedColumnName = "XESIS_CODIGO")
    @ManyToOne(optional = false)
    private XesisSiste xesisCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xeopcOpcio")
    private Collection<XeoxpOpcpe> xeoxpOpcpeCollection;

    public XeopcOpcio() {
    }

    public XeopcOpcio(String xeopcCodigo) {
        this.xeopcCodigo = xeopcCodigo;
    }

    public XeopcOpcio(String xeopcCodigo, String xeopcDescri) {
        this.xeopcCodigo = xeopcCodigo;
        this.xeopcDescri = xeopcDescri;
    }

    public String getXeopcCodigo() {
        return xeopcCodigo;
    }

    public void setXeopcCodigo(String xeopcCodigo) {
        this.xeopcCodigo = xeopcCodigo;
    }

    public String getXeopcDescri() {
        return xeopcDescri;
    }

    public void setXeopcDescri(String xeopcDescri) {
        this.xeopcDescri = xeopcDescri;
    }

    public XesisSiste getXesisCodigo() {
        return xesisCodigo;
    }

    public void setXesisCodigo(XesisSiste xesisCodigo) {
        this.xesisCodigo = xesisCodigo;
    }

    @XmlTransient
    public Collection<XeoxpOpcpe> getXeoxpOpcpeCollection() {
        return xeoxpOpcpeCollection;
    }

    public void setXeoxpOpcpeCollection(Collection<XeoxpOpcpe> xeoxpOpcpeCollection) {
        this.xeoxpOpcpeCollection = xeoxpOpcpeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeopcCodigo != null ? xeopcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeopcOpcio)) {
            return false;
        }
        XeopcOpcio other = (XeopcOpcio) object;
        if ((this.xeopcCodigo == null && other.xeopcCodigo != null) || (this.xeopcCodigo != null && !this.xeopcCodigo.equals(other.xeopcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.XeopcOpcio[ xeopcCodigo=" + xeopcCodigo + " ]";
    }
    
}
