package ec.edu.monster.controllers;

import ec.edu.monster.model.XeoxpOpcpe;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.facade.XeoxpOpcpeFacade;

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

@Named("xeoxpOpcpeController")
@SessionScoped
public class XeoxpOpcpeController implements Serializable {

    @EJB
    private ec.edu.monster.facade.XeoxpOpcpeFacade ejbFacade;
    private List<XeoxpOpcpe> items = null;
    private XeoxpOpcpe selected;

    public XeoxpOpcpeController() {
    }

    public XeoxpOpcpe getSelected() {
        return selected;
    }

    public void setSelected(XeoxpOpcpe selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getXeoxpOpcpePK().setXeperCodigo(selected.getXeperPerfi().getXeperCodigo());
        selected.getXeoxpOpcpePK().setXeopcCodigo(selected.getXeopcOpcio().getXeopcCodigo());
    }

    protected void initializeEmbeddableKey() {
        selected.setXeoxpOpcpePK(new ec.edu.monster.model.XeoxpOpcpePK());
    }

    private XeoxpOpcpeFacade getFacade() {
        return ejbFacade;
    }

    public XeoxpOpcpe prepareCreate() {
        selected = new XeoxpOpcpe();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("XeoxpOpcpeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("XeoxpOpcpeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("XeoxpOpcpeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<XeoxpOpcpe> getItems() {
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

    public XeoxpOpcpe getXeoxpOpcpe(ec.edu.monster.model.XeoxpOpcpePK id) {
        return getFacade().find(id);
    }

    public List<XeoxpOpcpe> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<XeoxpOpcpe> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = XeoxpOpcpe.class)
    public static class XeoxpOpcpeControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            XeoxpOpcpeController controller = (XeoxpOpcpeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "xeoxpOpcpeController");
            return controller.getXeoxpOpcpe(getKey(value));
        }

        ec.edu.monster.model.XeoxpOpcpePK getKey(String value) {
            ec.edu.monster.model.XeoxpOpcpePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ec.edu.monster.model.XeoxpOpcpePK();
            key.setXeperCodigo(values[0]);
            key.setXeopcCodigo(values[1]);
            key.setXeoxpFecasi(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(ec.edu.monster.model.XeoxpOpcpePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getXeperCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getXeopcCodigo());
            sb.append(SEPARATOR);
            sb.append(value.getXeoxpFecasi());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof XeoxpOpcpe) {
                XeoxpOpcpe o = (XeoxpOpcpe) object;
                return getStringKey(o.getXeoxpOpcpePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), XeoxpOpcpe.class.getName()});
                return null;
            }
        }

    }

}
