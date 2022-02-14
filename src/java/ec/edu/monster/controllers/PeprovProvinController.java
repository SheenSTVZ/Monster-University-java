package ec.edu.monster.controllers;

import ec.edu.monster.model.PeprovProvin;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.facade.PeprovProvinFacade;

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

@Named("peprovProvinController")
@SessionScoped
public class PeprovProvinController implements Serializable {

    @EJB
    private ec.edu.monster.facade.PeprovProvinFacade ejbFacade;
    private List<PeprovProvin> items = null;
    private PeprovProvin selected;

    public PeprovProvinController() {
    }

    public PeprovProvin getSelected() {
        return selected;
    }

    public void setSelected(PeprovProvin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getPeprovProvinPK().setPepaisCodigo(selected.getPepaisPais().getPepaisCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setPeprovProvinPK(new ec.edu.monster.model.PeprovProvinPK());
    }

    private PeprovProvinFacade getFacade() {
        return ejbFacade;
    }

    public PeprovProvin prepareCreate() {
        selected = new PeprovProvin();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PeprovProvinCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PeprovProvinUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PeprovProvinDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PeprovProvin> getItems() {
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

    public PeprovProvin getPeprovProvin(ec.edu.monster.model.PeprovProvinPK id) {
        return getFacade().find(id);
    }

    public List<PeprovProvin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PeprovProvin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PeprovProvin.class)
    public static class PeprovProvinControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeprovProvinController controller = (PeprovProvinController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "peprovProvinController");
            return controller.getPeprovProvin(getKey(value));
        }

        ec.edu.monster.model.PeprovProvinPK getKey(String value) {
            ec.edu.monster.model.PeprovProvinPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.edu.monster.model.PeprovProvinPK();
            key.setPepaisCodigo(values[0]);
            key.setPeprovCodigo(values[1]);
            return key;
        }

        String getStringKey(ec.edu.monster.model.PeprovProvinPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPepaisCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPeprovCodigo());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PeprovProvin) {
                PeprovProvin o = (PeprovProvin) object;
                return getStringKey(o.getPeprovProvinPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PeprovProvin.class.getName()});
                return null;
            }
        }

    }

}
