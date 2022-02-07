/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jhoan
 */
@Embeddable
public class PecarCargoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "PEDEP_CODIGO")
    private String pedepCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "PECAR_CODIGO")
    private String pecarCodigo;

    public PecarCargoPK() {
    }

    public PecarCargoPK(String pedepCodigo, String pecarCodigo) {
        this.pedepCodigo = pedepCodigo;
        this.pecarCodigo = pecarCodigo;
    }

    public String getPedepCodigo() {
        return pedepCodigo;
    }

    public void setPedepCodigo(String pedepCodigo) {
        this.pedepCodigo = pedepCodigo;
    }

    public String getPecarCodigo() {
        return pecarCodigo;
    }

    public void setPecarCodigo(String pecarCodigo) {
        this.pecarCodigo = pecarCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedepCodigo != null ? pedepCodigo.hashCode() : 0);
        hash += (pecarCodigo != null ? pecarCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PecarCargoPK)) {
            return false;
        }
        PecarCargoPK other = (PecarCargoPK) object;
        if ((this.pedepCodigo == null && other.pedepCodigo != null) || (this.pedepCodigo != null && !this.pedepCodigo.equals(other.pedepCodigo))) {
            return false;
        }
        if ((this.pecarCodigo == null && other.pecarCodigo != null) || (this.pecarCodigo != null && !this.pecarCodigo.equals(other.pecarCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.PecarCargoPK[ pedepCodigo=" + pedepCodigo + ", pecarCodigo=" + pecarCodigo + " ]";
    }
    
}
