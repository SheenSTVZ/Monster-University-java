package ec.edu.monster.controllers;

import ec.edu.monster.model.PecarCargo;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.facade.PecarCargoFacade;

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

@Named("pecarCargoController")
@SessionScoped
public class PecarCargoController implements Serializable {

    @EJB
    private ec.edu.monster.facade.PecarCargoFacade ejbFacade;
    private List<PecarCargo> items = null;
    private PecarCargo selected;

    public PecarCargoController() {
    }

    public PecarCargo getSelected() {
        return selected;
    }

    public void setSelected(PecarCargo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getPecarCargoPK().setPedepCodigo(selected.getPedepDepar().getPedepCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setPecarCargoPK(new ec.edu.monster.model.PecarCargoPK());
    }

    private PecarCargoFacade getFacade() {
        return ejbFacade;
    }

    public PecarCargo prepareCreate() {
        selected = new PecarCargo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PecarCargoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PecarCargoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PecarCargoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PecarCargo> getItems() {
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

    public PecarCargo getPecarCargo(ec.edu.monster.model.PecarCargoPK id) {
        return getFacade().find(id);
    }

    public List<PecarCargo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PecarCargo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PecarCargo.class)
    public static class PecarCargoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PecarCargoController controller = (PecarCargoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pecarCargoController");
            return controller.getPecarCargo(getKey(value));
        }

        ec.edu.monster.model.PecarCargoPK getKey(String value) {
            ec.edu.monster.model.PecarCargoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.edu.monster.model.PecarCargoPK();
            key.setPedepCodigo(values[0]);
            key.setPecarCodigo(values[1]);
            return key;
        }

        String getStringKey(ec.edu.monster.model.PecarCargoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPedepCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPecarCodigo());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PecarCargo) {
                PecarCargo o = (PecarCargo) object;
                return getStringKey(o.getPecarCargoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PecarCargo.class.getName()});
                return null;
            }
        }

    }

}
