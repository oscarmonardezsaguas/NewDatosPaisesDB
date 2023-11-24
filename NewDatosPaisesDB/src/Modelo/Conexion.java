package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
         /* Constante para conectarse con el servidor Santo Tomas
        private final String DRIVER="com.mysql.cj.jdbc.Driver";
        private final String URL="jdbc:mysql://10.68.0.251:18083/";
        private final String DB="world";
        private final String USER="root";
        private final String PASSWORD="alpro18"; */
        
    
        //Constante para conectarse con el servidor de tu PC
        private final String DRIVER="com.mysql.cj.jdbc.Driver";
        private final String URL="jdbc:mysql://localhost:3306/";
        private final String DB="world";
        private final String USER="root";
        private final String PASSWORD="data23";
    
        public Connection cadena;
        public static Conexion instancia;

        // constructor privado
        private Conexion() {
            this.cadena=null;
        }
        
        // conectar a una base de datos
        public Connection  conectar() {
                try {
                        Class.forName(DRIVER);
                        this.cadena=DriverManager.getConnection(URL+DB,USER,PASSWORD);
                } catch (ClassNotFoundException  | SQLException e) {
                    JOptionPane.showConfirmDialog(null, e.getMessage());
                    System.exit(0);
                }
                return this.cadena;
        }        
        
        // metodo desconectar de la base de datos
        public void desconectar(){
                try {
                        this.cadena.close();
                } catch (SQLException e) {
                    JOptionPane.showConfirmDialog(null, e.getMessage());                  
                }                
        }
        
        // patron de diseño singlenton para conectar solo 1 instancia a la base dedatos
        public synchronized static Conexion getInstancia(){
        if (instancia==null){
            instancia=new Conexion();
        }
        return instancia;
    }
        
}
