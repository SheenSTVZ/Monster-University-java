package ec.edu.monster.jsf;

import ec.edu.monster.model.BeedaEntalm;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.jpaController.BeedaEntalmFacade;

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

@Named("beedaEntalmController")
@SessionScoped
public class BeedaEntalmController implements Serializable {

    @EJB
    private ec.edu.monster.jpaController.BeedaEntalmFacade ejbFacade;
    private List<BeedaEntalm> items = null;
    private BeedaEntalm selected;

    public BeedaEntalmController() {
    }

    public BeedaEntalm getSelected() {
        return selected;
    }

    public void setSelected(BeedaEntalm selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BeedaEntalmFacade getFacade() {
        return ejbFacade;
    }

    public BeedaEntalm prepareCreate() {
        selected = new BeedaEntalm();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("BeedaEntalmCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BeedaEntalmUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("BeedaEntalmDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<BeedaEntalm> getItems() {
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

    public BeedaEntalm getBeedaEntalm(java.lang.Short id) {
        return getFacade().find(id);
    }

    public List<BeedaEntalm> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<BeedaEntalm> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = BeedaEntalm.class)
    public static class BeedaEntalmControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BeedaEntalmController controller = (BeedaEntalmController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "beedaEntalmController");
            return controller.getBeedaEntalm(getKey(value));
        }

        java.lang.Short getKey(String value) {
            java.lang.Short key;
            key = Short.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Short value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof BeedaEntalm) {
                BeedaEntalm o = (BeedaEntalm) object;
                return getStringKey(o.getBeedaNumero2());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), BeedaEntalm.class.getName()});
                return null;
            }
        }

    }

}
