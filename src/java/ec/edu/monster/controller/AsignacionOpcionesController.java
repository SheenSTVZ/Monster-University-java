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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author jhoan
 */
@Named(value = "asignacionOpcionesController")
@SessionScoped
public class AsignacionOpcionesController implements Serializable {

    
    private TreeNode root;
    private Object[] opcionSeleccionada;
    private String perfil;
    private List<List<XeopcOpcio>> opcionesdisp = new ArrayList();
    private List<XeoxpOpcpe> opcionesPorPerfil = new ArrayList();
    private int totOpciones = 0;
    private boolean updating = false;
    HashMap<Integer, Object> option_values = new HashMap();
    boolean canReassign = true;
    
    @EJB
    private ec.edu.monster.facade.XesisSisteFacade sistemasFacade;
    @EJB
    private ec.edu.monster.facade.XeoxpOpcpeFacade opcionPerfilFacade;
    @EJB
    private ec.edu.monster.facade.XeopcOpcioFacade opcionFacade;




    /**
     * Creates a new instance of AsignacionOpcionesController
     */
    public AsignacionOpcionesController() {
    }
    
    @PostConstruct
    public void init() {

        initTree();
    }
    
    public void initTree() {
        totOpciones = 0;
        root = null;
        List<XesisSiste> sistemas = sistemasFacade.findAll();
        option_values = new HashMap();
        root = new DefaultTreeNode("Root", null);
        
        
        opcionesPorPerfil = getOpcionesPerfil();
        System.out.println(opcionesPorPerfil);
        sistemas.forEach(sistema -> {
            TreeNode t = new DefaultTreeNode(sistema.getXesisDescri(), root);
                    List<XeopcOpcio> opciones = (List<XeopcOpcio>) sistema.getXeopcOpcioCollection();
                    TreeNode tmp;
                    if (opciones.size() > 0) {
                        opcionesdisp.add(opciones);
 
                        for (int l = 0; l < opcionesPorPerfil.size(); l++) {
                            for (int k = 0; k < opciones.size(); k++) {
                                if (opciones.get(k).getXesisCodigo().equals(opcionesPorPerfil.get(l).getXeopcOpcio().getXesisCodigo())) {
                                    System.out.println("Asignado");
                                    option_values.put(totOpciones, opciones.get(k));
                                }
                            }
                        }
                        totOpciones++;
                        //opcionSeleccionada.add(opciones.get(0));
                        tmp = new DefaultTreeNode("Opc", opciones, t);
                    }
        });
        for (int i = 0; i < totOpciones; i++) {
            if (!option_values.containsKey(i)) {
                option_values.put(i, null);
            }
        }
        opcionSeleccionada = new Object[totOpciones];
    }
 
    public int getValue(List<XeopcOpcio> opciones) {
        
        return (int) opcionesdisp.indexOf(opciones);
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
 
    public void manageChange(AjaxBehaviorEvent evt) {
        System.out.println("mc");
        System.out.println(evt.getBehavior());
        System.out.println(option_values);
    }
 
    public void manageChange2(ValueChangeEvent evt) {
        System.out.println("mc2");
        if (evt.getNewValue() != null) {
            System.out.println(evt.getNewValue());
        }
        if (evt.getOldValue() != null && evt.getNewValue() == null) {
            System.out.println(evt.getOldValue());
            //delete
            XeopcOpcio t = (XeopcOpcio) evt.getOldValue();
            opcionPerfilFacade.remove(opcionPerfilFacade.getElement(perfil,
                    t.getXeopcCodigo()));
        }
    }
 
    @Asynchronous
    private void persistOptions() {
        for (Map.Entry<Integer, Object> entry : option_values.entrySet()) {
 
            String value = (String) entry.getValue();
            
            if (value != null) {
                XeoxpOpcpe t = new XeoxpOpcpe();
                System.out.println(value);
                XeopcOpcio opcion1 = opcionFacade.getOpcion(value);
                
                XeoxpOpcpePK OpcpePK = new XeoxpOpcpePK();
                
                OpcpePK.setXeopcCodigo(opcion1.getXeopcCodigo());
                OpcpePK.setXeperCodigo(perfil);
                OpcpePK.setXeoxpFecasi(new Date());

                t.setXeperPerfi(new XeperPerfi(perfil));
                t.setXeopcOpcio(opcion1);
                t.setXeoxpOpcpePK(OpcpePK);
                
 
                opcionesPorPerfil.forEach(ope -> {
                    if (ope.getXeperPerfi().getXeperCodigo().equals(perfil)) {
                        ope.getXeperPerfi().getXeoxpOpcpeCollection().forEach(opcion -> {
                            System.out.println(opcion);
                            System.out.println(value);
                            if (opcion.getXeopcOpcio().getXesisCodigo().equals(value)) {
                                canReassign = false;
                            }
                        });
                    }
                });
                if (canReassign) {
                    opcionPerfilFacade.create(t);
                }
                canReassign = true;
            }
        }
        updating = false;
        JsfUtil.addSuccessMessage("Perfil actualizado con éxito");
    }
 
    public void save() {
        System.out.println(option_values);
        updating = true;
        JsfUtil.addSuccessMessage("Actualizando perfil ...");
        persistOptions();
 
    }
 
    public String getPerfil() {
        return perfil;
    }
 
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
 
    public Object[] getOpcionSeleccionada() {
        return opcionSeleccionada;
    }
 
    /*public void setOpcionSeleccionada(Object[] opcionSeleccionada) {
        this.opcionSeleccionada = opcionSeleccionada;
    }*/
    public List<List<XeopcOpcio>> getOpcionesdisp() {
        return opcionesdisp;
    }
 
    public void setOpcionesdisp(List<List<XeopcOpcio>> opcionesdisp) {
        this.opcionesdisp = opcionesdisp;
    }
 
    public List<XeoxpOpcpe> getOpcionesPorPerfil() {
        return opcionesPorPerfil;
    }
 
    public void setOpcionesPorPerfil(List<XeoxpOpcpe> opcionesPorPerfil) {
        this.opcionesPorPerfil = opcionesPorPerfil;
    }
 
    public Object getOpcionS(List<XeopcOpcio> opciones) {
        return opcionSeleccionada[opcionesdisp.indexOf(opciones)];
    }
 
    public HashMap<Integer, Object> getOption_values() {
        return option_values;
    }
 
    public void setOption_values(HashMap<Integer, Object> option_values) {
        this.option_values = option_values;
    }
 
    public boolean isUpdating() {
        return updating;
    }
 
    public void setUpdating(boolean updating) {
        this.updating = updating;
    } 
}

   
