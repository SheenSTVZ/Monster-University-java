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
@Table(name = "pedep_depar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedepDepar.findAll", query = "SELECT p FROM PedepDepar p")
    , @NamedQuery(name = "PedepDepar.findByPedepCodigo", query = "SELECT p FROM PedepDepar p WHERE p.pedepCodigo = :pedepCodigo")
    , @NamedQuery(name = "PedepDepar.findByPedepDescri", query = "SELECT p FROM PedepDepar p WHERE p.pedepDescri = :pedepDescri")})
public class PedepDepar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "PEDEP_CODIGO")
    private String pedepCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PEDEP_DESCRI")
    private String pedepDescri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedepDepar")
    private Collection<PecarCargo> pecarCargoCollection;

    public PedepDepar() {
    }

    public PedepDepar(String pedepCodigo) {
        this.pedepCodigo = pedepCodigo;
    }

    public PedepDepar(String pedepCodigo, String pedepDescri) {
        this.pedepCodigo = pedepCodigo;
        this.pedepDescri = pedepDescri;
    }

    public String getPedepCodigo() {
        return pedepCodigo;
    }

    public void setPedepCodigo(String pedepCodigo) {
        this.pedepCodigo = pedepCodigo;
    }

    public String getPedepDescri() {
        return pedepDescri;
    }

    public void setPedepDescri(String pedepDescri) {
        this.pedepDescri = pedepDescri;
    }

    @XmlTransient
    public Collection<PecarCargo> getPecarCargoCollection() {
        return pecarCargoCollection;
    }

    public void setPecarCargoCollection(Collection<PecarCargo> pecarCargoCollection) {
        this.pecarCargoCollection = pecarCargoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedepCodigo != null ? pedepCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedepDepar)) {
            return false;
        }
        PedepDepar other = (PedepDepar) object;
        if ((this.pedepCodigo == null && other.pedepCodigo != null) || (this.pedepCodigo != null && !this.pedepCodigo.equals(other.pedepCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.PedepDepar[ pedepCodigo=" + pedepCodigo + " ]";
    }
    
}
