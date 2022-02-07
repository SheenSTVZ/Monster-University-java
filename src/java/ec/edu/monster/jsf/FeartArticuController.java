package ec.edu.monster.jsf;

import ec.edu.monster.model.FeartArticu;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.jpaController.FeartArticuFacade;

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

@Named("feartArticuController")
@SessionScoped
public class FeartArticuController implements Serializable {

    @EJB
    private ec.edu.monster.jpaController.FeartArticuFacade ejbFacade;
    private List<FeartArticu> items = null;
    private FeartArticu selected;

    public FeartArticuController() {
    }

    public FeartArticu getSelected() {
        return selected;
    }

    public void setSelected(FeartArticu selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FeartArticuFacade getFacade() {
        return ejbFacade;
    }

    public FeartArticu prepareCreate() {
        selected = new FeartArticu();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FeartArticuCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FeartArticuUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FeartArticuDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<FeartArticu> getItems() {
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

    public FeartArticu getFeartArticu(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<FeartArticu> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<FeartArticu> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = FeartArticu.class)
    public static class FeartArticuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FeartArticuController controller = (FeartArticuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "feartArticuController");
            return controller.getFeartArticu(getKey(value));
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
            if (object instanceof FeartArticu) {
                FeartArticu o = (FeartArticu) object;
                return getStringKey(o.getFeartCodigo2());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FeartArticu.class.getName()});
                return null;
            }
        }

    }

}
