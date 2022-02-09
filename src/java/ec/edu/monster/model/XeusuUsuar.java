/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhoan
 */
@Entity
@Table(name = "xeusu_usuar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XeusuUsuar.findAll", query = "SELECT x FROM XeusuUsuar x")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuCodigo", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuCodigo = :xeusuCodigo")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuPaswd", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuPaswd = :xeusuPaswd")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuFeccre", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuFeccre = :xeusuFeccre")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuFecmod", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuFecmod = :xeusuFecmod")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuPiefir", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuPiefir = :xeusuPiefir")})
public class XeusuUsuar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "XEUSU_CODIGO")
    private String xeusuCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEUSU_PASWD")
    private String xeusuPaswd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "XEUSU_FECCRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date xeusuFeccre;
    @Column(name = "XEUSU_FECMOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date xeusuFecmod;
    @Size(max = 100)
    @Column(name = "XEUSU_PIEFIR")
    private String xeusuPiefir;
    @JoinColumn(name = "FEEMP_CODIGO", referencedColumnName = "FEEMP_CODIGO")
    @ManyToOne(optional = false)
    private FeempEmple feempCodigo;
    @JoinColumn(name = "XEEST_CODIGO", referencedColumnName = "XEEST_CODIGO")
    @ManyToOne(optional = false)
    private XeestEstad xeestCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xeusuUsuar")
    private Collection<XeuxpUsupe> xeuxpUsupeCollection;

    public XeusuUsuar() {
    }

    public XeusuUsuar(String xeusuCodigo) {
        this.xeusuCodigo = xeusuCodigo;
    }

    public XeusuUsuar(String xeusuCodigo, String xeusuPaswd, Date xeusuFeccre) {
        this.xeusuCodigo = xeusuCodigo;
        this.xeusuPaswd = xeusuPaswd;
        this.xeusuFeccre = xeusuFeccre;
    }

    public String getXeusuCodigo() {
        return xeusuCodigo;
    }

    public void setXeusuCodigo(String xeusuCodigo) {
        this.xeusuCodigo = xeusuCodigo;
    }

    public String getXeusuPaswd() {
        return xeusuPaswd;
    }

    public void setXeusuPaswd(String xeusuPaswd) {
        this.xeusuPaswd = xeusuPaswd;
    }

    public Date getXeusuFeccre() {
        return xeusuFeccre;
    }

    public void setXeusuFeccre(Date xeusuFeccre) {
        this.xeusuFeccre = xeusuFeccre;
    }

    public Date getXeusuFecmod() {
        return xeusuFecmod;
    }

    public void setXeusuFecmod(Date xeusuFecmod) {
        this.xeusuFecmod = xeusuFecmod;
    }

    public String getXeusuPiefir() {
        return xeusuPiefir;
    }

    public void setXeusuPiefir(String xeusuPiefir) {
        this.xeusuPiefir = xeusuPiefir;
    }

    public FeempEmple getFeempCodigo() {
        return feempCodigo;
    }

    public void setFeempCodigo(FeempEmple feempCodigo) {
        this.feempCodigo = feempCodigo;
    }

    public XeestEstad getXeestCodigo() {
        return xeestCodigo;
    }

    public void setXeestCodigo(XeestEstad xeestCodigo) {
        this.xeestCodigo = xeestCodigo;
    }

    @XmlTransient
    public Collection<XeuxpUsupe> getXeuxpUsupeCollection() {
        return xeuxpUsupeCollection;
    }

    public void setXeuxpUsupeCollection(Collection<XeuxpUsupe> xeuxpUsupeCollection) {
        this.xeuxpUsupeCollection = xeuxpUsupeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeusuCodigo != null ? xeusuCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeusuUsuar)) {
            return false;
        }
        XeusuUsuar other = (XeusuUsuar) object;
        if ((this.xeusuCodigo == null && other.xeusuCodigo != null) || (this.xeusuCodigo != null && !this.xeusuCodigo.equals(other.xeusuCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.XeusuUsuar[ xeusuCodigo=" + xeusuCodigo + " ]";
    }
    
}
