/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controller;

import ec.edu.monster.jpaController.XeusuUsuarFacade;
import ec.edu.monster.model.FeempEmple;
import ec.edu.monster.model.XeusuUsuar;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;


/**
 *
 * @author jhoan
 */
@Named(value = "loginController")
@ViewScoped
public class loginController implements Serializable{

    private XeusuUsuar usuario;
    @EJB
    private XeusuUsuarFacade usuarioFacade;
    /**
     * Creates a new instance of loginController
     */
    public XeusuUsuar getUsuario() {
        return usuario;
    }    
    
    @PostConstruct
    public void init() {
        XeusuUsuar x = (XeusuUsuar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if (x != null) {
            /*try {
                // Logged in - redirect
                //FacesContext.getCurrentInstance().getExternalContext().redirect("/home.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } else {
            System.out.println("NA");
        }
    }
 
    /**
     * Creates a new instance of loginController
     */
    
    public loginController() {
        usuario =new XeusuUsuar();
        usuario.setFeempCodigo(new FeempEmple());
    }
    
    public void setUsuario(XeusuUsuar usuario) {
        this.usuario = usuario;
    }
 
    public void doLogin() throws NoSuchAlgorithmException, IOException {
        
        hash hashPswd = new hash();
        
        String password = hashPswd.md5(usuario.getXeusuPaswd());
        
        if (usuarioFacade.doLogin(usuario.getFeempCodigo().getFeempEmail(), password)) {
 
            XeusuUsuar x = (XeusuUsuar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
 
            if (x.getXeusuPaswd() != null) {
                // Logged in - redirect
 
                FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
 
            } else {
                System.out.println(x.getXeusuPaswd());
//                FacesContext.getCurrentInstance().getExternalContext().redirect("cambiopass.xhtml");
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Credenciales incorrectas"));
        }
    }
 
    public void doLogout() throws NoSuchAlgorithmException, IOException {
        System.out.println("logout");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
 
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
 
    }
}

