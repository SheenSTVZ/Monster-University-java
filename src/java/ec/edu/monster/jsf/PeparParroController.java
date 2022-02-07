package ec.edu.monster.jsf;

import ec.edu.monster.model.PeparParro;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.jpaController.PeparParroFacade;

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

@Named("peparParroController")
@SessionScoped
public class PeparParroController implements Serializable {

    @EJB
    private ec.edu.monster.jpaController.PeparParroFacade ejbFacade;
    private List<PeparParro> items = null;
    private PeparParro selected;

    public PeparParroController() {
    }

    public PeparParro getSelected() {
        return selected;
    }

    public void setSelected(PeparParro selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getPeparParroPK().setPeprovCodigo(selected.getPecanCanton().getPecanCantonPK().getPeprovCodigo());
        selected.getPeparParroPK().setPepaisCodigo(selected.getPecanCanton().getPecanCantonPK().getPepaisCodigo());
        selected.getPeparParroPK().setPecanCodigo(selected.getPecanCanton().getPecanCantonPK().getPecanCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setPeparParroPK(new ec.edu.monster.model.PeparParroPK());
    }

    private PeparParroFacade getFacade() {
        return ejbFacade;
    }

    public PeparParro prepareCreate() {
        selected = new PeparParro();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PeparParroCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PeparParroUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PeparParroDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PeparParro> getItems() {
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

    public PeparParro getPeparParro(ec.edu.monster.model.PeparParroPK id) {
        return getFacade().find(id);
    }

    public List<PeparParro> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PeparParro> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PeparParro.class)
    public static class PeparParroControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeparParroController controller = (PeparParroController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "peparParroController");
            return controller.getPeparParro(getKey(value));
        }

        ec.edu.monster.model.PeparParroPK getKey(String value) {
            ec.edu.monster.model.PeparParroPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.edu.monster.model.PeparParroPK();
            key.setPepaisCodigo(values[0]);
            key.setPeprovCodigo(values[1]);
            key.setPecanCodigo(values[2]);
            key.setPeparCodigo(values[3]);
            return key;
        }

        String getStringKey(ec.edu.monster.model.PeparParroPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPepaisCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPeprovCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPecanCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getPeparCodigo());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PeparParro) {
                PeparParro o = (PeparParro) object;
                return getStringKey(o.getPeparParroPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PeparParro.class.getName()});
                return null;
            }
        }

    }

}
