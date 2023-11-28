package Negocio;

import Modelo.Conexion;
import Modelo.PaisDAO;
import Controlador.Pais;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PaisNegocio 
{
    private final PaisDAO DATOS;
    private Pais obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    private String codigo;
    private String nombre;
    private String continente;
    private int poblacion; 
 
   
   public PaisNegocio()
   {      
        this.DATOS=new PaisDAO();       
        this.obj=new Pais();
   }
   
   // metodo lista paises
 public DefaultTableModel listar(String texto){
        List<Pais> lista=new ArrayList();
        lista.addAll(DATOS.listar(texto));        
        String[] titulos={"Codigo","Nombre","Continente","Poblacion"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        
        
        String estado;
        String[] registro = new String[10];
        
        this.registrosMostrados=0;
        for (Pais item:lista){          
            registro[0]=item.getCodePais();
            registro[1]=item.getNombre();
            registro[2]=item.getContinente();
            registro[3]=Integer.toString( item.getPoblacion());
          
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
 
 // metodo para guardar datos en la tabla
  public String insertar(String codigo, String nombre, String continente, int poblacion){
        if (DATOS.existe(nombre)){
            return "ATENCION, el registro ya existe...";
        }else{
            obj.setCodePais(codigo);
            obj.setNombre(nombre);
            obj.setContinente(continente);
            obj.setPoblacion(poblacion);         
            if (DATOS.insertar(obj)){
                return "ATENCION, registro almacenado correctamente...";
            }else{
                return "ATENCION, ocurrio un error al guardar el registro.";
            }
        }
    }           
  
   // metodo para actualizar datos en la tabla
  public String actualizar(String codigo, String nombre, String continente, int poblacion){
        if (DATOS.existe(nombre)){
            return "ATENCION, el registro ya existe...";
        }else{
            obj.setCodePais(codigo);
            obj.setNombre(nombre);
            obj.setContinente(continente);
            obj.setPoblacion(poblacion);         
            if (DATOS.actualizar(obj)){
                return "ATENCION, registro almacenado correctamente...";
            }else{
                return "ATENCION, ocurrio un error al guardar el registro.";
            }
        }
    }      
  
   // metodo eliminar paises
 public String eliminar(String texto){
     
        return null;     
    }
       
        
}
