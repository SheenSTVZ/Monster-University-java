package ec.edu.monster.jsf;

import ec.edu.monster.model.FedocDeorco;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.jpaController.FedocDeorcoFacade;

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

@Named("fedocDeorcoController")
@SessionScoped
public class FedocDeorcoController implements Serializable {

    @EJB
    private ec.edu.monster.jpaController.FedocDeorcoFacade ejbFacade;
    private List<FedocDeorco> items = null;
    private FedocDeorco selected;

    public FedocDeorcoController() {
    }

    public FedocDeorco getSelected() {
        return selected;
    }

    public void setSelected(FedocDeorco selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FedocDeorcoFacade getFacade() {
        return ejbFacade;
    }

    public FedocDeorco prepareCreate() {
        selected = new FedocDeorco();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FedocDeorcoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FedocDeorcoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FedocDeorcoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<FedocDeorco> getItems() {
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

    public FedocDeorco getFedocDeorco(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<FedocDeorco> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<FedocDeorco> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = FedocDeorco.class)
    public static class FedocDeorcoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FedocDeorcoController controller = (FedocDeorcoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fedocDeorcoController");
            return controller.getFedocDeorco(getKey(value));
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
            if (object instanceof FedocDeorco) {
                FedocDeorco o = (FedocDeorco) object;
                return getStringKey(o.getFecocNumero2());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FedocDeorco.class.getName()});
                return null;
            }
        }

    }

}
