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
@Table(name = "feprv_prov")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeprvProv.findAll", query = "SELECT f FROM FeprvProv f")
    , @NamedQuery(name = "FeprvProv.findByFeprvCodigo3", query = "SELECT f FROM FeprvProv f WHERE f.feprvCodigo3 = :feprvCodigo3")
    , @NamedQuery(name = "FeprvProv.findByBeprvRuc", query = "SELECT f FROM FeprvProv f WHERE f.beprvRuc = :beprvRuc")
    , @NamedQuery(name = "FeprvProv.findByBeprvRazsoc", query = "SELECT f FROM FeprvProv f WHERE f.beprvRazsoc = :beprvRazsoc")
    , @NamedQuery(name = "FeprvProv.findByBeprvDirec", query = "SELECT f FROM FeprvProv f WHERE f.beprvDirec = :beprvDirec")
    , @NamedQuery(name = "FeprvProv.findByBeprvTelef", query = "SELECT f FROM FeprvProv f WHERE f.beprvTelef = :beprvTelef")})
public class FeprvProv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "FEPRV_CODIGO3")
    private String feprvCodigo3;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feprvCodigo3")
    private Collection<FecocCaor> fecocCaorCollection;

    public FeprvProv() {
    }

    public FeprvProv(String feprvCodigo3) {
        this.feprvCodigo3 = feprvCodigo3;
    }

    public FeprvProv(String feprvCodigo3, String beprvRuc, String beprvRazsoc, String beprvDirec, String beprvTelef) {
        this.feprvCodigo3 = feprvCodigo3;
        this.beprvRuc = beprvRuc;
        this.beprvRazsoc = beprvRazsoc;
        this.beprvDirec = beprvDirec;
        this.beprvTelef = beprvTelef;
    }

    public String getFeprvCodigo3() {
        return feprvCodigo3;
    }

    public void setFeprvCodigo3(String feprvCodigo3) {
        this.feprvCodigo3 = feprvCodigo3;
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
    public Collection<FecocCaor> getFecocCaorCollection() {
        return fecocCaorCollection;
    }

    public void setFecocCaorCollection(Collection<FecocCaor> fecocCaorCollection) {
        this.fecocCaorCollection = fecocCaorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feprvCodigo3 != null ? feprvCodigo3.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeprvProv)) {
            return false;
        }
        FeprvProv other = (FeprvProv) object;
        if ((this.feprvCodigo3 == null && other.feprvCodigo3 != null) || (this.feprvCodigo3 != null && !this.feprvCodigo3.equals(other.feprvCodigo3))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.FeprvProv[ feprvCodigo3=" + feprvCodigo3 + " ]";
    }
    
}
