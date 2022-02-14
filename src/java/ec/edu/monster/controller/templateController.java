/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controller;

import ec.edu.monster.facade.XeuxpUsupeFacade;
import ec.edu.monster.model.XeusuUsuar;
import ec.edu.monster.model.XeuxpUsupe;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;


/**
 *
 * @author jhoan
 */
@Named(value = "templateController")
@SessionScoped
public class templateController implements Serializable  {

    private XeusuUsuar logged;
    private XeuxpUsupe op;
    @EJB
    private XeuxpUsupeFacade usuPer;
    
    
    
    /**
     * Creates a new instance of templateController
     */
    
    
    @PostConstruct
    public void init() {
        op = usuPer.getUsuarioPerfil(logged);
        if(op!=null){
            System.out.println(op);
        }
    }
    
    
    public templateController() {
        logged = (XeusuUsuar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if (logged != null) {
            System.out.println("PRUEBA DE FUNCIONAMIENTO");
            System.out.println(logged); 
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/PROYECTO_ARIAS_GOMEZ_ROSERO/faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(templateController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("NA");
        }
    }

    public XeusuUsuar getLogged() {
        return logged;
    }

    public void setLogged(XeusuUsuar logged) {
        this.logged = logged;
    }

//    public XeuxpUsupe getOp() {
//        return op;
//    }
//
//    public void setOp(XeuxpUsupe op) {
//        this.op = op;
//    }
    
    
    public boolean profileIsAllowed(String perfil) {
        if(op!=null){
            if(op.getXeperPerfi().getXeperDescri().equals(perfil)|| op.getXeperPerfi().getXeperDescri().equals("ADMINISTRADOR"))
            {
                return true;
            }
        }
        return false;
    }
 
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
}

