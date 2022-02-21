/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.facade;

import ec.edu.monster.model.XeopcOpcio;
import ec.edu.monster.model.XeuxpUsupe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jhoan
 */
@Stateless
public class XeopcOpcioFacade extends AbstractFacade<XeopcOpcio> {

    @PersistenceContext(unitName = "PROYECTO_ARIAS_GOMEZ_ROSEROPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XeopcOpcioFacade() {
        super(XeopcOpcio.class);
    }
    
    public XeopcOpcio getOpcion(String codPerfil) {
 
        TypedQuery<XeopcOpcio> query
                = getEntityManager().createNamedQuery("XeopcOpcio.findByXeopcCodigo", XeopcOpcio.class);
        return query.setParameter("xeopcCodigo", codPerfil).getSingleResult();
    }
    
}
