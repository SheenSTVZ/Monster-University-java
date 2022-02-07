package ec.edu.monster.jsf;

import ec.edu.monster.model.XesisSiste;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.jpaController.XesisSisteFacade;

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

@Named("xesisSisteController")
@SessionScoped
public class XesisSisteController implements Serializable {

    @EJB
    private ec.edu.monster.jpaController.XesisSisteFacade ejbFacade;
    private List<XesisSiste> items = null;
    private XesisSiste selected;

    public XesisSisteController() {
    }

    public XesisSiste getSelected() {
        return selected;
    }

    public void setSelected(XesisSiste selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private XesisSisteFacade getFacade() {
        return ejbFacade;
    }

    public XesisSiste prepareCreate() {
        selected = new XesisSiste();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("XesisSisteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("XesisSisteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("XesisSisteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<XesisSiste> getItems() {
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

    public XesisSiste getXesisSiste(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<XesisSiste> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<XesisSiste> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = XesisSiste.class)
    public static class XesisSisteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            XesisSisteController controller = (XesisSisteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "xesisSisteController");
            return controller.getXesisSiste(getKey(value));
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
            if (object instanceof XesisSiste) {
                XesisSiste o = (XesisSiste) object;
                return getStringKey(o.getXesisCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), XesisSiste.class.getName()});
                return null;
            }
        }

    }

}
