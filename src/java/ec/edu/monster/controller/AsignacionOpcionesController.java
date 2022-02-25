/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controller;

import ec.edu.monster.jsf.util.JsfUtil;
import ec.edu.monster.model.XeopcOpcio;
import ec.edu.monster.model.XeoxpOpcpe;
import ec.edu.monster.model.XeperPerfi;
import ec.edu.monster.model.XesisSiste;
import ec.edu.monster.controllers.XeopcOpcioController;
import ec.edu.monster.model.XeoxpOpcpePK;
import ec.edu.monster.model.XevenVentan;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.Cacheable;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author jhoan
 */
@Named(value = "asignacionOpcionesController")
@Cacheable(false)
@SessionScoped
public class AsignacionOpcionesController implements Serializable {

    private TreeNode root;
    private String perfil;
    private TreeNode[] selectedNodes;
    private List<XeoxpOpcpe> opcionesPorPerfil = new ArrayList();
    @EJB
    private ec.edu.monster.facade.XesisSisteFacade sistemasFacade;
    @EJB
    private ec.edu.monster.facade.XeoxpOpcpeFacade opcionPerfilFacade;

    @EJB
    private ec.edu.monster.facade.XevenVentanFacade ventanaFacade;

    /**
     * Creates a new instance of AsignacionOpcionesController
     */
    public AsignacionOpcionesController() {
        
    }

    @PostConstruct
    public void init() {
        System.out.println(sistemasFacade);
        initTree();
        
    }

    public void initTree() {
        root = null;
        List<XesisSiste> sistemas = sistemasFacade.findAll();
        root = new DefaultTreeNode("Root", null);
        opcionesPorPerfil = getOpcionesPerfil();
        System.out.println(opcionesPorPerfil);
        sistemas.forEach(sistema -> {
            TreeNode t = new DefaultTreeNode(sistema.getXesisDescri(), root);
            List<XevenVentan> ventanas = (List<XevenVentan>) ventanaFacade.findAll();
            ventanas.forEach(ventana -> {

                TreeNode v = new DefaultTreeNode(ventana.getXevenDescri(), t);
                List<XeopcOpcio> opciones = (List<XeopcOpcio>) ventana.getXeopcOpcioCollection();

                if (opciones.size() > 0) {

                    opciones.forEach(opcion -> {

                        if (ventana.equals(opcion.getXevenCodigo()) && sistema.equals(opcion.getXesisCodigo())) {

                            List<XeoxpOpcpe> opcionesSeleccionadas = (List<XeoxpOpcpe>) opcionPerfilFacade.findAll();
                            TreeNode o = new DefaultTreeNode("opc", opcion, v);
                            
                            opcionesSeleccionadas.forEach(op -> {
                                
                                if(!o.isSelected()){
                                    
                                }
                                
                                if(op.getXeopcOpcio().equals(opcion) && op.getXeperPerfi().getXeperCodigo().equals(perfil))
                                {
                                    o.setSelected(true);
                                }
                            
                            });

                        }

                    });
                }

            });
        });
    }

    public void displaySelectedMultiple(TreeNode[] nodes) {
        if (nodes != null && nodes.length > 0) {
            StringBuilder builder = new StringBuilder();

            for (TreeNode node : nodes) {
                if (node.getChildCount() == 0) {
                    
                    try{
                        
                            XeopcOpcio opcion = (XeopcOpcio) node.getData();

                            XeoxpOpcpe t = new XeoxpOpcpe();

                            XeoxpOpcpePK OpcpePK = new XeoxpOpcpePK();

                            OpcpePK.setXeopcCodigo(opcion.getXeopcCodigo());
                            OpcpePK.setXeperCodigo(perfil);
                            OpcpePK.setXeoxpFecasi(new Date());

                            t.setXeperPerfi(new XeperPerfi(perfil));
                            t.setXeopcOpcio(opcion);
                            t.setXeoxpOpcpePK(OpcpePK);

                            opcionPerfilFacade.create(t);

    //                    builder.append(node.getData().toString());
                            builder.append("Se ha creado correctamente");
                            builder.append("<br />");
                       
                    
                    }catch(Exception e){
                        System.out.println(e);
                        builder.append("No hay opciones para " + node.getData().toString());
                        builder.append("<br />");
                    }

                    
                }
                System.out.println(node.getData().toString());
                                
            }
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", builder.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public List<XeoxpOpcpe> getOpcionesPerfil() {
        return opcionPerfilFacade.getOpPer(perfil);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void getValueOf(Object n) {
        System.out.println(n);
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<XeoxpOpcpe> getOpcionesPorPerfil() {
        return opcionesPorPerfil;
    }

    public void setOpcionesPorPerfil(List<XeoxpOpcpe> opcionesPorPerfil) {
        this.opcionesPorPerfil = opcionesPorPerfil;
    }
}
