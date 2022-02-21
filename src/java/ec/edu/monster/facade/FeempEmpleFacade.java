/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.facade;

import ec.edu.monster.model.FeempEmple;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jhoan
 */
@Stateless
public class FeempEmpleFacade extends AbstractFacade<FeempEmple> {

    @PersistenceContext(unitName = "PROYECTO_ARIAS_GOMEZ_ROSEROPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeempEmpleFacade() {
        super(FeempEmple.class);
    }
    
    public List<FeempEmple> findEmpleados(String codusu, Date inicio, Date ffinal) {
        if(inicio != null || ffinal != null){
            return getEntityManager().createNativeQuery("SELECT * "
                + "FROM feemp_emple u "
                + "WHERE u.PESEX_CODIGO like ? AND u.FEEMP_FECNAC BETWEEN ? AND ?", FeempEmple.class)
                .setParameter(1, codusu).setParameter(2, inicio).setParameter(3, ffinal).getResultList();
        }
        return getEntityManager().createNativeQuery("SELECT * "
                + "FROM feemp_emple u "
                + "WHERE u.PESEX_CODIGO like ? OR u.FEEMP_FECNAC BETWEEN ? AND ?", FeempEmple.class)
                .setParameter(1, codusu).setParameter(2, inicio).setParameter(3, ffinal).getResultList();
    }
    
}
