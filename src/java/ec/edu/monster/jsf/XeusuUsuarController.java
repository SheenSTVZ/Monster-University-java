package ec.edu.monster.jsf;

import ec.edu.monster.controller.passwordController;
import ec.edu.monster.model.XeusuUsuar;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.jpaController.XeusuUsuarFacade;
import ec.edu.monster.model.FeempEmple;
import java.io.IOException;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("xeusuUsuarController")
@SessionScoped
public class XeusuUsuarController implements Serializable {

    @EJB
    private ec.edu.monster.jpaController.XeusuUsuarFacade ejbFacade;
    private List<XeusuUsuar> items = null;
    private XeusuUsuar selected;
    private XeusuUsuar cambio;
    private String password = null;
    private String newPassword = null;
    private String antPassword = null;
    private String repPassword = null;
    private FeempEmple selectedPK;


    public XeusuUsuarController() {
    }

    public FeempEmple getSelectedPK() {
        return selectedPK;
    }

    public void setSelectedPK(FeempEmple selectedPK) {
        this.selectedPK = selectedPK;
    }

    public XeusuUsuar getSelected() {
        return selected;
    }

    public void setSelected(XeusuUsuar selected) {
        this.selected = selected;
    }

    public XeusuUsuar getCambio() {
        return cambio;
    }

    public void setCambio(XeusuUsuar cambio) {
        this.cambio = cambio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAntPassword() {
        return antPassword;
    }

    public void setAntPassword(String antPassword) {
        this.antPassword = antPassword;
    }

    public String getRepPassword() {
        return repPassword;
    }

    public void setRepPassword(String repPassword) {
        this.repPassword = repPassword;
    }

    
    
    protected void setEmbeddableKeys() {

    }

    protected void initializeEmbeddableKey() {
        
    }

    private XeusuUsuarFacade getFacade() {
        return ejbFacade;
    }

    public XeusuUsuar prepareCreate() {
        selected = new XeusuUsuar();
        initializeEmbeddableKey();
        return selected;
    }
    
    public XeusuUsuar obtenerDatosUsu() throws NoSuchAlgorithmException {

        cambio = (XeusuUsuar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        System.out.println(cambio.getFeempCodigo());
        cambio.setXeusuPaswd(passwordController.md5(newPassword));// setUsuContrasena(generateHash(newPassword));
        System.out.println("----------------------------------------------------------------------------------------------");
        //  System.out.println("--CONT" +usuarioPass.getUsuContIngreso());
        //  System.out.println("--NOM"+ usuarioPass.getUsuNombre());
        // System.out.println(usuarioPass.getUsuContrasena() + " -> " + usuarioPass.getUsuNombre() + " -> " + usuarioPass.getUsuContrasenaAntigua0());
        System.out.println("----------------------------------------------------------------------------------------------");
        return cambio;
    }

    public void cambiarPass() {
        try {
            selected = obtenerDatosUsu();
            if (selected.getFeempCodigo().getFeempEmail().equals(newPassword)) {
                //RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos inválidos", "Usuario y contraseña no pueden ser iguales"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos inválidos", "Usuario y contraseña no pueden ser iguales"));
            } else if (!newPassword.equals(repPassword)) {
                // RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos inválidos", "Contraseñas no coinciden"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos inválidos", "Contraseñas no coinciden"));
            } else {
                persist(PersistAction.UPDATE, "SE HA CAMBIADO LA CLAVE");
                //  RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos actualizados", "Sus datos han sido actualizados"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos actualizados", "Sus datos han sido actualizados"));
                FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(XeusuUsuarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XeusuUsuarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void create() {
        String claveAleatoria = passwordController.getRandomString(8);
        selected.setXeusuPaswd(passwordController.md5(claveAleatoria));
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("XeusuUsuarCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("XeusuUsuarUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("XeusuUsuarDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<XeusuUsuar> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public XeusuUsuar getXeusuUsuar(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<XeusuUsuar> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<XeusuUsuar> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = XeusuUsuar.class)
    public static class XeusuUsuarControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            XeusuUsuarController controller = (XeusuUsuarController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "xeusuUsuarController");
            return controller.getXeusuUsuar(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof XeusuUsuar) {
                XeusuUsuar o = (XeusuUsuar) object;
                return getStringKey(o.getXeusuCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), XeusuUsuar.class.getName()});
                return null;
            }
        }

    }

}
