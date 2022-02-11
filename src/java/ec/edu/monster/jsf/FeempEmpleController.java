package ec.edu.monster.jsf;

import ec.edu.monster.controller.passwordController;
import ec.edu.monster.model.FeempEmple;
import ec.edu.monster.model.XeusuUsuar;
import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.jsf.util.JsfUtil.PersistAction;
import ec.edu.monster.jpaController.FeempEmpleFacade;
import ec.edu.monster.model.XeestEstad;
import java.io.ByteArrayInputStream;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Named("feempEmpleController")
@SessionScoped
public class FeempEmpleController implements Serializable {

    @EJB
    private ec.edu.monster.jpaController.FeempEmpleFacade ejbFacade;
    
    @EJB
    private ec.edu.monster.jpaController.XeusuUsuarFacade usuFacade;

    
    private List<FeempEmple> items = null;
    private FeempEmple selected;
    private XeusuUsuar usuario;
    private StreamedContent imagepreview;
    private UploadedFile image;

    public FeempEmpleController() {
    }
    public XeusuUsuar getUsuario() {
        return usuario;
    }

    public void setUsuario(XeusuUsuar usuario) {
        this.usuario = usuario;
    }
    public StreamedContent getImagepreview() {
        return imagepreview;
    }
 
    public void setImagepreview(StreamedContent imagepreview) {
        this.imagepreview = imagepreview;
    }

    
    public FeempEmple getSelected() {
        return selected;
    }

    public void setSelected(FeempEmple selected) {
        this.selected = selected;
        this.usuario = usuFacade.findUsu(selected.getFeempCodigo());

        System.out.println(usuario);
        
        this.imagepreview = DefaultStreamedContent.builder()
                .contentType("image/*")
                .stream(()->{
                    try{
                        return new ByteArrayInputStream(Base64.getDecoder().decode(selected.getFeempFoto().getBytes()));
                    }catch(Exception e){
                        e.printStackTrace();
                        return null;
                    }
                }).build();
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FeempEmpleFacade getFacade() {
        return ejbFacade;
    }

    public FeempEmple prepareCreate() {
        selected = new FeempEmple();
        initializeEmbeddableKey();
        usuario = new XeusuUsuar();
        usuario.setFeempCodigo(new FeempEmple());
        image = null;
        imagepreview = null;
        return selected;
    }

    public String preview(FileUploadEvent event) {
        image = event.getFile();
        System.out.println("Uploaded");
        System.out.println(event.getFile().getFileName());
        imagepreview = DefaultStreamedContent.builder()
                .contentType("image/*")
                .stream(() -> {
                    try {
                        return new ByteArrayInputStream(event.getFile().getContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .build();
        System.out.println(Base64.getEncoder().encodeToString(image.getContent()));
        return Base64.getEncoder().encodeToString(image.getContent());
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FeempEmpleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FeempEmpleUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FeempEmpleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<FeempEmple> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void enviarConGmail(String destinatario, String asunto, String cuerpo) throws AddressException, MessagingException{
        String remitente = "soportemonsteruniversity1";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "Empanada123");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try{
            message.setFrom(new InternetAddress(remitente));
            InternetAddress[] addresses = InternetAddress.parse(destinatario);
            message.addRecipients(Message.RecipientType.TO, addresses);
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, "Empanada123");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch (MessagingException me){
            me.printStackTrace();
        }
    }
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                      selected.setFeempFoto(Base64.getEncoder().encodeToString(image.getContent()));
                    getFacade().edit(selected);
                    if (persistAction == PersistAction.CREATE) {
                            usuario.getFeempCodigo().setFeempCodigo(selected.getFeempCodigo());
                            usuario.setXeusuCodigo(selected.getFeempCodigo());
                             String claveAleatoria = passwordController.getRandomString(8);
                             Date date = new Date();
                             XeestEstad estado = new XeestEstad();
                estado.setXeestCodigo("0");
                estado.setXeestDescri("Inactivo");
                             usuario.setXeusuPaswd(passwordController.md5(claveAleatoria));
                             usuario.setXeusuFeccre(date);
                             usuario.setXeestCodigo(estado);
                             System.out.println(usuario);
                             enviarConGmail(selected.getFeempEmail(), "Contraseña", "Su contraseña de primer acceso es: "+claveAleatoria);
                             usuFacade.edit(usuario);
                        }

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

    public FeempEmple getFeempEmple(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<FeempEmple> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<FeempEmple> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = FeempEmple.class)
    public static class FeempEmpleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FeempEmpleController controller = (FeempEmpleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "feempEmpleController");
            return controller.getFeempEmple(getKey(value));
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
            if (object instanceof FeempEmple) {
                FeempEmple o = (FeempEmple) object;
                return getStringKey(o.getFeempCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FeempEmple.class.getName()});
                return null;
            }
        }

    }

}
