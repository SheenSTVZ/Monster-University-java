/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.facade;

import ec.edu.monster.model.XeoxpOpcpe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jhoan
 */
@Stateless
public class XeoxpOpcpeFacade extends AbstractFacade<XeoxpOpcpe> {

    @PersistenceContext(unitName = "PROYECTO_ARIAS_GOMEZ_ROSEROPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XeoxpOpcpeFacade() {
        super(XeoxpOpcpe.class);
    }
    
    
    
    
    public XeoxpOpcpe getElement(String perfil, String op) {
        return (XeoxpOpcpe) em.createNativeQuery("select * from xeoxp_opcpe where XEPER_CODIGO like ? AND XEOPC_CODIGO like ?",
                XeoxpOpcpe.class).setParameter(1, perfil).setParameter(2, op).getSingleResult();
    }

}
