/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.jpaController;

import ec.edu.monster.model.XeusuUsuar;
import javax.ejb.Stateless;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author jhoan
 */
@Stateless
public class XeusuUsuarFacade extends AbstractFacade<XeusuUsuar> {

    @PersistenceContext(unitName = "PROYECTO_ARIAS_GOMEZ_ROSEROPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XeusuUsuarFacade() {
        super(XeusuUsuar.class);
    }
    public List<XeusuUsuar> getUsuariosNotRelated() {
        return getEntityManager().createNativeQuery("SELECT * FROM xeusu_usuar x WHERE x.FEEMP_CODIGO NOT IN (SELECT up.XEUSU_CODIGO from xeuxp_usupe up);", XeusuUsuar.class)
                .getResultList();
 
    }
 
    public XeusuUsuar findUsu(String id) {
        try {
            return (XeusuUsuar) em.createNativeQuery("SELECT * FROM xeusu_usuar WHERE FEEMP_CODIGO LIKE " + id + "", XeusuUsuar.class).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
 
//    public XeperPerfil getUsuPerfil(String correo) {
//        try {
//            return (XeperPerfil) em.createNativeQuery("select * "
//                    + "from xeper_perfil x "
//                    + "inner join xeuxp_usuper p on p.xeper_codper = x.xeper_codper "
//                    + "where p.xeusu_email like '" + correo + "'", XeperPerfil.class).getSingleResult();
//        } catch (Exception e) {
//            return null;
//        }
//    }

    public Boolean doLogin(String correo, String pass) {
 
        try {
            XeusuUsuar obj = (XeusuUsuar) em.createNativeQuery("SELECT * FROM xeusu_usuar WHERE FEEMP_CODIGO LIKE (SELECT FEEMP_CODIGO FROM feemp_emple WHERE FEEMP_EMAIL LIKE  '" + correo + "') AND XEUSU_PASWD LIKE '" + pass + "'", XeusuUsuar.class).getSingleResult();
            System.out.println(obj.getFeempCodigo().getFeempEmail());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    public Boolean searchEmail(String correo) {
        try {
            em.createNativeQuery("SELECT * FROM feemp_emple WHERE FEEMP_EMAIL LIKE '" + correo + "'").getSingleResult();
            System.out.println("existe");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
}

