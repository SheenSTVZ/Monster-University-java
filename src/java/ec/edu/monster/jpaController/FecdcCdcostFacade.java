/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.jpaController;

import ec.edu.monster.model.FecdcCdcost;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jhoan
 */
@Stateless
public class FecdcCdcostFacade extends AbstractFacade<FecdcCdcost> {

    @PersistenceContext(unitName = "PROYECTO_ARIAS_GOMEZ_ROSEROPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FecdcCdcostFacade() {
        super(FecdcCdcost.class);
    }
    
}
