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
import javax.persistence.Lob;
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
@Table(name = "beprv_prov")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BeprvProv.findAll", query = "SELECT b FROM BeprvProv b")
    , @NamedQuery(name = "BeprvProv.findByBeprvCodigo2", query = "SELECT b FROM BeprvProv b WHERE b.beprvCodigo2 = :beprvCodigo2")
    , @NamedQuery(name = "BeprvProv.findByBeprvRuc", query = "SELECT b FROM BeprvProv b WHERE b.beprvRuc = :beprvRuc")
    , @NamedQuery(name = "BeprvProv.findByBeprvRazsoc", query = "SELECT b FROM BeprvProv b WHERE b.beprvRazsoc = :beprvRazsoc")
    , @NamedQuery(name = "BeprvProv.findByBeprvDirec", query = "SELECT b FROM BeprvProv b WHERE b.beprvDirec = :beprvDirec")
    , @NamedQuery(name = "BeprvProv.findByBeprvTelef", query = "SELECT b FROM BeprvProv b WHERE b.beprvTelef = :beprvTelef")})
public class BeprvProv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "BEPRV_CODIGO2")
    private String beprvCodigo2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "BEPRV_RUC")
    private String beprvRuc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEPRV_RAZSOC")
    private String beprvRazsoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "BEPRV_DIREC")
    private String beprvDirec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEPRV_TELEF")
    private String beprvTelef;
    @Lob
    @Size(max = 65535)
    @Column(name = "BEPRV_OBSER")
    private String beprvObser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beprvCodigo2")
    private Collection<BeedaEntalm> beedaEntalmCollection;

    public BeprvProv() {
    }

    public BeprvProv(String beprvCodigo2) {
        this.beprvCodigo2 = beprvCodigo2;
    }

    public BeprvProv(String beprvCodigo2, String beprvRuc, String beprvRazsoc, String beprvDirec, String beprvTelef) {
        this.beprvCodigo2 = beprvCodigo2;
        this.beprvRuc = beprvRuc;
        this.beprvRazsoc = beprvRazsoc;
        this.beprvDirec = beprvDirec;
        this.beprvTelef = beprvTelef;
    }

    public String getBeprvCodigo2() {
        return beprvCodigo2;
    }

    public void setBeprvCodigo2(String beprvCodigo2) {
        this.beprvCodigo2 = beprvCodigo2;
    }

    public String getBeprvRuc() {
        return beprvRuc;
    }

    public void setBeprvRuc(String beprvRuc) {
        this.beprvRuc = beprvRuc;
    }

    public String getBeprvRazsoc() {
        return beprvRazsoc;
    }

    public void setBeprvRazsoc(String beprvRazsoc) {
        this.beprvRazsoc = beprvRazsoc;
    }

    public String getBeprvDirec() {
        return beprvDirec;
    }

    public void setBeprvDirec(String beprvDirec) {
        this.beprvDirec = beprvDirec;
    }

    public String getBeprvTelef() {
        return beprvTelef;
    }

    public void setBeprvTelef(String beprvTelef) {
        this.beprvTelef = beprvTelef;
    }

    public String getBeprvObser() {
        return beprvObser;
    }

    public void setBeprvObser(String beprvObser) {
        this.beprvObser = beprvObser;
    }

    @XmlTransient
    public Collection<BeedaEntalm> getBeedaEntalmCollection() {
        return beedaEntalmCollection;
    }

    public void setBeedaEntalmCollection(Collection<BeedaEntalm> beedaEntalmCollection) {
        this.beedaEntalmCollection = beedaEntalmCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (beprvCodigo2 != null ? beprvCodigo2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeprvProv)) {
            return false;
        }
        BeprvProv other = (BeprvProv) object;
        if ((this.beprvCodigo2 == null && other.beprvCodigo2 != null) || (this.beprvCodigo2 != null && !this.beprvCodigo2.equals(other.beprvCodigo2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.BeprvProv[ beprvCodigo2=" + beprvCodigo2 + " ]";
    }
    
}
