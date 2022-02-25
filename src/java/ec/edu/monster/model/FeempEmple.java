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
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
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
@Table(name = "feemp_emple")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeempEmple.findAll", query = "SELECT f FROM FeempEmple f")
    , @NamedQuery(name = "FeempEmple.findByFeempCodigo", query = "SELECT f FROM FeempEmple f WHERE f.feempCodigo = :feempCodigo")
    , @NamedQuery(name = "FeempEmple.findByFeempApelli", query = "SELECT f FROM FeempEmple f WHERE f.feempApelli = :feempApelli")
    , @NamedQuery(name = "FeempEmple.findByFeempNombre", query = "SELECT f FROM FeempEmple f WHERE f.feempNombre = :feempNombre")
    , @NamedQuery(name = "FeempEmple.findByFeempFecnac", query = "SELECT f FROM FeempEmple f WHERE f.feempFecnac = :feempFecnac")
    , @NamedQuery(name = "FeempEmple.findByFeempFecsal", query = "SELECT f FROM FeempEmple f WHERE f.feempFecsal = :feempFecsal")
    , @NamedQuery(name = "FeempEmple.findByFeempDirec", query = "SELECT f FROM FeempEmple f WHERE f.feempDirec = :feempDirec")
    , @NamedQuery(name = "FeempEmple.findByFeempTelef", query = "SELECT f FROM FeempEmple f WHERE f.feempTelef = :feempTelef")
    , @NamedQuery(name = "FeempEmple.findByFeempEmail", query = "SELECT f FROM FeempEmple f WHERE f.feempEmail = :feempEmail")
    , @NamedQuery(name = "FeempEmple.findByFeempCedula", query = "SELECT f FROM FeempEmple f WHERE f.feempCedula = :feempCedula")
    , @NamedQuery(name = "FeempEmple.findByFeempCarfam", query = "SELECT f FROM FeempEmple f WHERE f.feempCarfam = :feempCarfam")
    , @NamedQuery(name = "FeempEmple.findByFeempPasapo", query = "SELECT f FROM FeempEmple f WHERE f.feempPasapo = :feempPasapo")
    , @NamedQuery(name = "FeempEmple.findByFeempDiscapa", query = "SELECT f FROM FeempEmple f WHERE f.feempDiscapa = :feempDiscapa")})
public class FeempEmple implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "FEEMP_CODIGO")
    private String feempCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FEEMP_APELLI")
    private String feempApelli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FEEMP_NOMBRE")
    private String feempNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEEMP_FECNAC")
    @Temporal(TemporalType.DATE)
    private Date feempFecnac;
    @Column(name = "FEEMP_FECSAL")
    @Temporal(TemporalType.DATE)
    private Date feempFecsal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "FEEMP_DIREC")
    private String feempDirec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "FEEMP_TELEF")
    private String feempTelef;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FEEMP_EMAIL")
    private String feempEmail;
    @Size(max = 10)
    @Column(name = "FEEMP_CEDULA")
    private String feempCedula;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "FEEMP_FOTO")
    private String feempFoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEEMP_CARFAM")
    private short feempCarfam;
    @Size(max = 15)
    @Column(name = "FEEMP_PASAPO")
    private String feempPasapo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEEMP_DISCAPA")
    private int feempDiscapa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feempCodigo")
    private Collection<XeusuUsuar> xeusuUsuarCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feempCodigo")
    private Collection<FecsoCabsol> fecsoCabsolCollection;
    @JoinColumn(name = "BEALM_CODIGO", referencedColumnName = "BEALM_CODIGO")
    @ManyToOne
    private BealmAlmacn bealmCodigo;
    @JoinColumn(name = "FECDC_CODIGO", referencedColumnName = "FECDC_CODIGO")
    @ManyToOne
    private FecdcCdcost fecdcCodigo;
    @JoinColumns({
        @JoinColumn(name = "PEDEP_CODIGO", referencedColumnName = "PEDEP_CODIGO")
        , @JoinColumn(name = "PECAR_CODIGO", referencedColumnName = "PECAR_CODIGO")})
    @ManyToOne(optional = false)
    private PecarCargo pecarCargo;
    @JoinColumn(name = "PEESC_CODIGO", referencedColumnName = "PEESC_CODIGO")
    @ManyToOne
    private PeescEstciv peescCodigo;
    @JoinColumn(name = "PESEX_CODIGO", referencedColumnName = "PESEX_CODIGO")
    @ManyToOne(optional = false)
    private PesexSexo pesexCodigo;
    @JoinColumns({
        @JoinColumn(name = "PEPAIS_CODIGO", referencedColumnName = "PEPAIS_CODIGO")
        , @JoinColumn(name = "PEPROV_CODIGO", referencedColumnName = "PEPROV_CODIGO")
        , @JoinColumn(name = "PECAN_CODIGO", referencedColumnName = "PECAN_CODIGO")
        , @JoinColumn(name = "PEPAR_CODIGO", referencedColumnName = "PEPAR_CODIGO")})
    @ManyToOne(optional = false)
    private PeparParro peparParro;

    public FeempEmple() {
    }

    public FeempEmple(String feempCodigo) {
        this.feempCodigo = feempCodigo;
    }

    public FeempEmple(String feempCodigo, String feempApelli, String feempNombre, Date feempFecnac, String feempDirec, String feempTelef, String feempEmail, short feempCarfam, int feempDiscapa) {
        this.feempCodigo = feempCodigo;
        this.feempApelli = feempApelli;
        this.feempNombre = feempNombre;
        this.feempFecnac = feempFecnac;
        this.feempDirec = feempDirec;
        this.feempTelef = feempTelef;
        this.feempEmail = feempEmail;
        this.feempCarfam = feempCarfam;
        this.feempDiscapa = feempDiscapa;
    }

    public String getFeempCodigo() {
        return feempCodigo;
    }

    public void setFeempCodigo(String feempCodigo) {
        this.feempCodigo = feempCodigo;
    }

    public String getFeempApelli() {
        return feempApelli;
    }

    public void setFeempApelli(String feempApelli) {
        this.feempApelli = feempApelli;
    }

    public String getFeempNombre() {
        return feempNombre;
    }

    public void setFeempNombre(String feempNombre) {
        this.feempNombre = feempNombre;
    }

    public Date getFeempFecnac() {
        return feempFecnac;
    }

    public void setFeempFecnac(Date feempFecnac) {
        this.feempFecnac = feempFecnac;
    }

    public Date getFeempFecsal() {
        return feempFecsal;
    }

    public void setFeempFecsal(Date feempFecsal) {
        this.feempFecsal = feempFecsal;
    }

    public String getFeempDirec() {
        return feempDirec;
    }

    public void setFeempDirec(String feempDirec) {
        this.feempDirec = feempDirec;
    }

    public String getFeempTelef() {
        return feempTelef;
    }

    public void setFeempTelef(String feempTelef) {
        this.feempTelef = feempTelef;
    }

    public String getFeempEmail() {
        return feempEmail;
    }

    public void setFeempEmail(String feempEmail) {
        this.feempEmail = feempEmail;
    }

    public String getFeempCedula() {
        return feempCedula;
    }

    public void setFeempCedula(String feempCedula) {
        this.feempCedula = feempCedula;
    }

    public String getFeempFoto() {
        return feempFoto;
    }

    public void setFeempFoto(String feempFoto) {
        this.feempFoto = feempFoto;
    }

    public short getFeempCarfam() {
        return feempCarfam;
    }

    public void setFeempCarfam(short feempCarfam) {
        this.feempCarfam = feempCarfam;
    }

    public String getFeempPasapo() {
        return feempPasapo;
    }

    public void setFeempPasapo(String feempPasapo) {
        this.feempPasapo = feempPasapo;
    }

    public int getFeempDiscapa() {
        return feempDiscapa;
    }

    public void setFeempDiscapa(int feempDiscapa) {
        this.feempDiscapa = feempDiscapa;
    }

    @XmlTransient
    public Collection<XeusuUsuar> getXeusuUsuarCollection() {
        return xeusuUsuarCollection;
    }

    public void setXeusuUsuarCollection(Collection<XeusuUsuar> xeusuUsuarCollection) {
        this.xeusuUsuarCollection = xeusuUsuarCollection;
    }

    @XmlTransient
    public Collection<FecsoCabsol> getFecsoCabsolCollection() {
        return fecsoCabsolCollection;
    }

    public void setFecsoCabsolCollection(Collection<FecsoCabsol> fecsoCabsolCollection) {
        this.fecsoCabsolCollection = fecsoCabsolCollection;
    }

    public BealmAlmacn getBealmCodigo() {
        return bealmCodigo;
    }

    public void setBealmCodigo(BealmAlmacn bealmCodigo) {
        this.bealmCodigo = bealmCodigo;
    }

    public FecdcCdcost getFecdcCodigo() {
        return fecdcCodigo;
    }

    public void setFecdcCodigo(FecdcCdcost fecdcCodigo) {
        this.fecdcCodigo = fecdcCodigo;
    }

    public PecarCargo getPecarCargo() {
        return pecarCargo;
    }

    public void setPecarCargo(PecarCargo pecarCargo) {
        this.pecarCargo = pecarCargo;
    }

    public PeescEstciv getPeescCodigo() {
        return peescCodigo;
    }

    public void setPeescCodigo(PeescEstciv peescCodigo) {
        this.peescCodigo = peescCodigo;
    }

    public PesexSexo getPesexCodigo() {
        return pesexCodigo;
    }

    public void setPesexCodigo(PesexSexo pesexCodigo) {
        this.pesexCodigo = pesexCodigo;
    }

    public PeparParro getPeparParro() {
        return peparParro;
    }

    public void setPeparParro(PeparParro peparParro) {
        this.peparParro = peparParro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feempCodigo != null ? feempCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeempEmple)) {
            return false;
        }
        FeempEmple other = (FeempEmple) object;
        if ((this.feempCodigo == null && other.feempCodigo != null) || (this.feempCodigo != null && !this.feempCodigo.equals(other.feempCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.FeempEmple[ feempCodigo=" + feempCodigo + " ]";
    }
    
}
