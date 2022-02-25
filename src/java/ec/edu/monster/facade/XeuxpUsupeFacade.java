/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.facade;

import ec.edu.monster.model.XeusuUsuar;
import ec.edu.monster.model.XeuxpUsupe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author jhoan
 */
@Stateless
public class XeuxpUsupeFacade extends AbstractFacade<XeuxpUsupe> {

    @PersistenceContext(unitName = "PROYECTO_ARIAS_GOMEZ_ROSEROPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XeuxpUsupeFacade() {
        super(XeuxpUsupe.class);
    }
    
    public XeuxpUsupe getUsuarioPerfil(XeusuUsuar codigoUsu){
        Query query
                = getEntityManager().createNativeQuery("SELECT * FROM xeuxp_usupe WHERE XEUSU_CODIGO LIKE ?", XeuxpUsupe.class);
        try{
            return (XeuxpUsupe) query.setParameter(1,codigoUsu.getXeusuCodigo()).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
    
    
    public List<XeuxpUsupe> getUsuarioPerfilP(String codPerfil) {
 
        TypedQuery<XeuxpUsupe> query
                = getEntityManager().createNamedQuery("XeuxpUsupe.findByXeperCodigo", XeuxpUsupe.class);
        return query.setParameter("xeperCodigo", codPerfil).getResultList();
    }
    
    public List<XeusuUsuar> findUsuarios(String codusu) {
 
        return getEntityManager().createNativeQuery("SELECT u.* "
                + "FROM xeuxp_usupe up, xeusu_usuar u "
                + "WHERE up.XEUSU_CODIGO = u.XEUSU_CODIGO AND up.XEPER_CODIGO like ? ", XeusuUsuar.class)
                .setParameter(1, codusu).getResultList();
    }
    
        public List<XeuxpUsupe> getUsuarios(String id) {
        System.out.println(id);
        TypedQuery<XeuxpUsupe> query
                = getEntityManager().createNamedQuery("XeuxpUsupe.findByXeperCodigo", XeuxpUsupe.class);
        System.out.println(query.setParameter("xeperCodigo", id).getResultList());
        return query.setParameter("xeperCodigo", id).getResultList();
    }
        
        
        
       @Transactional
    public void insertWithQuery(String idperfil, String id_persona, String email) {
        // Date date=new Date();
        java.util.Date fecha = new java.util.Date();
        System.out.println("ipd" + idperfil + "o " + id_persona);
        getEntityManager().createNativeQuery("INSERT INTO xeuxp_usupe (XEPER_CODIGO, XEUSU_CODIGO, XEUXP_FECASI, XEUXP_FECRET) VALUES (?,?,?,?)").setParameter(1, idperfil)
                .setParameter(2, id_persona).setParameter(3, fecha).setParameter(4, fecha).executeUpdate();
    }
 
    @Transactional
    public void removeAssignation(String id_persona) {
 
        getEntityManager().createNativeQuery("DELETE FROM xeuxp_usupe WHERE XEUSU_CODIGO like ?").setParameter(1, id_persona)
                .executeUpdate();
    }
    
}
