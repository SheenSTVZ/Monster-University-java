/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "fedso_detsol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FedsoDetsol.findAll", query = "SELECT f FROM FedsoDetsol f")
    , @NamedQuery(name = "FedsoDetsol.findByFecsoNumerosoli", query = "SELECT f FROM FedsoDetsol f WHERE f.fecsoNumerosoli = :fecsoNumerosoli")
    , @NamedQuery(name = "FedsoDetsol.findByFedsoResponsable", query = "SELECT f FROM FedsoDetsol f WHERE f.fedsoResponsable = :fedsoResponsable")
    , @NamedQuery(name = "FedsoDetsol.findByFedsoRubropresupuestal", query = "SELECT f FROM FedsoDetsol f WHERE f.fedsoRubropresupuestal = :fedsoRubropresupuestal")})
public class FedsoDetsol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECSO_NUMEROSOLI")
    private Short fecsoNumerosoli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "FEDSO_RESPONSABLE")
    private String fedsoResponsable;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEDSO_RUBROPRESUPUESTAL")
    private BigDecimal fedsoRubropresupuestal;
    @JoinColumn(name = "FECSO_NUMEROSOLI", referencedColumnName = "FECSO_NUMEROSOLI", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private FecsoCabsol fecsoCabsol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fecsoNumerosoli")
    private Collection<FeartArticu> feartArticuCollection;

    public FedsoDetsol() {
    }

    public FedsoDetsol(Short fecsoNumerosoli) {
        this.fecsoNumerosoli = fecsoNumerosoli;
    }

    public FedsoDetsol(Short fecsoNumerosoli, String fedsoResponsable, BigDecimal fedsoRubropresupuestal) {
        this.fecsoNumerosoli = fecsoNumerosoli;
        this.fedsoResponsable = fedsoResponsable;
        this.fedsoRubropresupuestal = fedsoRubropresupuestal;
    }

    public Short getFecsoNumerosoli() {
        return fecsoNumerosoli;
    }

    public void setFecsoNumerosoli(Short fecsoNumerosoli) {
        this.fecsoNumerosoli = fecsoNumerosoli;
    }

    public String getFedsoResponsable() {
        return fedsoResponsable;
    }

    public void setFedsoResponsable(String fedsoResponsable) {
        this.fedsoResponsable = fedsoResponsable;
    }

    public BigDecimal getFedsoRubropresupuestal() {
        return fedsoRubropresupuestal;
    }

    public void setFedsoRubropresupuestal(BigDecimal fedsoRubropresupuestal) {
        this.fedsoRubropresupuestal = fedsoRubropresupuestal;
    }

    public FecsoCabsol getFecsoCabsol() {
        return fecsoCabsol;
    }

    public void setFecsoCabsol(FecsoCabsol fecsoCabsol) {
        this.fecsoCabsol = fecsoCabsol;
    }

    @XmlTransient
    public Collection<FeartArticu> getFeartArticuCollection() {
        return feartArticuCollection;
    }

    public void setFeartArticuCollection(Collection<FeartArticu> feartArticuCollection) {
        this.feartArticuCollection = feartArticuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecsoNumerosoli != null ? fecsoNumerosoli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FedsoDetsol)) {
            return false;
        }
        FedsoDetsol other = (FedsoDetsol) object;
        if ((this.fecsoNumerosoli == null && other.fecsoNumerosoli != null) || (this.fecsoNumerosoli != null && !this.fecsoNumerosoli.equals(other.fecsoNumerosoli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.FedsoDetsol[ fecsoNumerosoli=" + fecsoNumerosoli + " ]";
    }
    
}
