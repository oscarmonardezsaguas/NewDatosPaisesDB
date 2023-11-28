package Modelo;

import Modelo.interfaces.CrudSimpleInterfaces;
import java.util.List;
import Controlador.Pais;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.Conexion;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PaisDAO  implements CrudSimpleInterfaces<Pais>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public PaisDAO() {
        CON = Conexion.getInstancia();
    }   
    
    @Override
    public List<Pais> listar(String texto) {
        List<Pais> registros=new ArrayList();
        try {
          ps=CON.conectar().prepareStatement("SELECT  code, name, continent, population FROM country  WHERE name LIKE ?");          
            ps.setString(1,"%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){               
                  registros.add(new Pais(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
            ps.close();
            rs.close();         
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        } finally {
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }
   
    @Override
    public boolean eliminar(String texto) {
     resp=false;
        try {
            ps=CON.conectar().prepareStatement("DELETE FROM country WHERE code=?");
            ps.setString(1, texto);
            rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
                resp=true;
            }           
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return resp; 
    }
     
    // verifica que el registro ingresado no 
    @Override
    public boolean existe(String texto) {
       resp=false;
        try {
            ps=CON.conectar().prepareStatement("SELECT name FROM country WHERE name=?");
            ps.setString(1, texto);
            rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
                resp=true;
            }           
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return resp;
    }

    // metodo insertar registro
    @Override
    public boolean insertar(Pais obj) {
    resp=false;
        try {
            ps=CON.conectar().prepareStatement("INSERT INTO country (code,name,continent, population) VALUES (?,?,?,?)");
            ps.setString(1, obj.getCodePais());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getContinente());
            ps.setInt(4, obj.getPoblacion());

            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Pais obj) {   
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE pais SET code=?, codigo=?, name=?, continent=?, population=? WHERE code=?");
            ps.setString(1, obj.getCodePais());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getContinente());
            ps.setInt(4, obj.getPoblacion());

            if (ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public  List<Pais> buscar (String texto) {
        List<Pais> registros=new ArrayList();
        try {
          ps=CON.conectar().prepareStatement("SELECT  code, name, continent, population FROM country  WHERE code= ?");          
            ps.setString(1, texto);
            rs=ps.executeQuery();
            while(rs.next()){               
                  registros.add(new Pais(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
            ps.close();
            rs.close();         
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        } finally {
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }
     

    
}
