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
public class PeparParroPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "PEPAIS_CODIGO")
    private String pepaisCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "PEPROV_CODIGO")
    private String peprovCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "PECAN_CODIGO")
    private String pecanCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "PEPAR_CODIGO")
    private String peparCodigo;

    public PeparParroPK() {
    }

    public PeparParroPK(String pepaisCodigo, String peprovCodigo, String pecanCodigo, String peparCodigo) {
        this.pepaisCodigo = pepaisCodigo;
        this.peprovCodigo = peprovCodigo;
        this.pecanCodigo = pecanCodigo;
        this.peparCodigo = peparCodigo;
    }

    public String getPepaisCodigo() {
        return pepaisCodigo;
    }

    public void setPepaisCodigo(String pepaisCodigo) {
        this.pepaisCodigo = pepaisCodigo;
    }

    public String getPeprovCodigo() {
        return peprovCodigo;
    }

    public void setPeprovCodigo(String peprovCodigo) {
        this.peprovCodigo = peprovCodigo;
    }

    public String getPecanCodigo() {
        return pecanCodigo;
    }

    public void setPecanCodigo(String pecanCodigo) {
        this.pecanCodigo = pecanCodigo;
    }

    public String getPeparCodigo() {
        return peparCodigo;
    }

    public void setPeparCodigo(String peparCodigo) {
        this.peparCodigo = peparCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pepaisCodigo != null ? pepaisCodigo.hashCode() : 0);
        hash += (peprovCodigo != null ? peprovCodigo.hashCode() : 0);
        hash += (pecanCodigo != null ? pecanCodigo.hashCode() : 0);
        hash += (peparCodigo != null ? peparCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeparParroPK)) {
            return false;
        }
        PeparParroPK other = (PeparParroPK) object;
        if ((this.pepaisCodigo == null && other.pepaisCodigo != null) || (this.pepaisCodigo != null && !this.pepaisCodigo.equals(other.pepaisCodigo))) {
            return false;
        }
        if ((this.peprovCodigo == null && other.peprovCodigo != null) || (this.peprovCodigo != null && !this.peprovCodigo.equals(other.peprovCodigo))) {
            return false;
        }
        if ((this.pecanCodigo == null && other.pecanCodigo != null) || (this.pecanCodigo != null && !this.pecanCodigo.equals(other.pecanCodigo))) {
            return false;
        }
        if ((this.peparCodigo == null && other.peparCodigo != null) || (this.peparCodigo != null && !this.peparCodigo.equals(other.peparCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.model.PeparParroPK[ pepaisCodigo=" + pepaisCodigo + ", peprovCodigo=" + peprovCodigo + ", pecanCodigo=" + pecanCodigo + ", peparCodigo=" + peparCodigo + " ]";
    }
    
}
