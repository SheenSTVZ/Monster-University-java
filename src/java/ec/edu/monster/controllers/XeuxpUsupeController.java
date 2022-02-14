package ec.edu.monster.controllers;

import ec.edu.monster.model.XeuxpUsupe;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.facade.XeuxpUsupeFacade;
import ec.edu.monster.model.XeusuUsuar;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("xeuxpUsupeController")
@SessionScoped
public class XeuxpUsupeController implements Serializable {

    @EJB
    private ec.edu.monster.facade.XeuxpUsupeFacade ejbFacade;
    private List<XeuxpUsupe> items = null;
    private XeuxpUsupe selected;
    private XeusuUsuar codUser = new XeusuUsuar();
    private XeusuUsuar codUserDel = new XeusuUsuar();
    private String perfil = "";

    public XeuxpUsupeController() {
    }

    public XeuxpUsupe getSelected() {
        return selected;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public XeusuUsuar getCodUserDel() {
        return codUserDel;
    }

    public void setCodUserDel(XeusuUsuar codUserDel) {
        this.codUserDel = codUserDel;
    }
    

    public void setSelected(XeuxpUsupe selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getXeuxpUsupePK().setXeperCodigo(selected.getXeperPerfi().getXeperCodigo());
        selected.getXeuxpUsupePK().setXeusuCodigo(selected.getXeusuUsuar().getXeusuCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setXeuxpUsupePK(new ec.edu.monster.model.XeuxpUsupePK());
    }

    private XeuxpUsupeFacade getFacade() {
        return ejbFacade;
    }

    public XeuxpUsupe prepareCreate() {
        selected = new XeuxpUsupe();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("XeuxpUsupeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("XeuxpUsupeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("XeuxpUsupeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<XeuxpUsupe> getItems() {
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

    public XeuxpUsupe getXeuxpUsupe(ec.edu.monster.model.XeuxpUsupePK id) {
        return getFacade().find(id);
    }

    public List<XeuxpUsupe> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<XeuxpUsupe> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = XeuxpUsupe.class)
    public static class XeuxpUsupeControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            XeuxpUsupeController controller = (XeuxpUsupeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "xeuxpUsupeController");
            return controller.getXeuxpUsupe(getKey(value));
        }

        ec.edu.monster.model.XeuxpUsupePK getKey(String value) {
            ec.edu.monster.model.XeuxpUsupePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.edu.monster.model.XeuxpUsupePK();
            key.setXeperCodigo(values[0]);
            key.setXeusuCodigo(values[1]);
            key.setXeuxpFecasi(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(ec.edu.monster.model.XeuxpUsupePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getXeperCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getXeusuCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getXeuxpFecasi());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof XeuxpUsupe) {
                XeuxpUsupe o = (XeuxpUsupe) object;
                return getStringKey(o.getXeuxpUsupePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), XeuxpUsupe.class.getName()});
                return null;
            }
        }

    }
    
    public void addAll(List<XeusuUsuar> users) {
        for (int i = 0; i < users.size(); i++) {
 
            getFacade().insertWithQuery(perfil, users.get(i).getFeempCodigo().getFeempCodigo(), users.get(i).getFeempCodigo().getFeempEmail());
        }
 
    }
    
    public void addOne() {
        // System.out.println(usuario);
        getFacade().insertWithQuery(perfil, codUser.getFeempCodigo().getFeempCodigo(), codUser.getFeempCodigo().getFeempEmail());
    }
 
    public void deleteAll() {
 
        List<XeuxpUsupe> users = getFacade().getUsuarioPerfilP(perfil);
        for (int i = 0; i < users.size(); i++) {
 
            getFacade().remove(users.get(i));
        }
 
    }
 
    public void deleteByCodPas() {
 
        getFacade().removeAssignation(codUserDel.getFeempCodigo().getFeempCodigo());
    }
 
    public List<XeusuUsuar> getUsuarios(String id) {
//        System.out.println( "usuario" +getFacade().findUsuarios(id).get(0).getXeusuUsuarPK().getXeusuEmail());
        if ((getFacade().findUsuarios(id).size()>0)) {
            getFacade().findUsuarios(id).get(0).getFeempCodigo().getFeempEmail();
        }
        return getFacade().findUsuarios(id);
    }
 
    public XeusuUsuar getCodUser() {
        return codUser;
    }
 
    public void setCodUser(XeusuUsuar codUser) {
        this.codUser = codUser;
    }



}
