/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Ciudad;
import Negocio.CiudadNegocio;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author conta
 */
public class VistaCiudad extends javax.swing.JFrame {
private final CiudadNegocio  CONTROL;
    
    private String nombreCiudad;
    private String paisPertenece;
    private String distrito;
    private int poblacionCiudad;
    private ImageIcon imagen;
    private Icon icono;
    
    ArrayList<Ciudad> lista = new ArrayList<>();

    public VistaCiudad() {
        initComponents();
          this.CONTROL = new CiudadNegocio();
        eventoBotones();
        metodoMouse();
        metodoMouse2();
        this.pintarImagen(jimagen, "src/main/java/Imagenes/fondo.jpg");
    }
    
     // metodo evento botones
    private void eventoBotones()
    {
        // accion del boton agregar
        ActionListener oyenteAgregar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    //crearDatos();
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error en el formato de la población", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error al crear datos", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }          
        };  
        jButtonCrear.addActionListener(oyenteAgregar); 
        
        // accion del boton eliminar
        ActionListener oyenteEliminar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    //eliminarDatos();
                }
                catch(NullPointerException ex)
                {
                     JOptionPane.showMessageDialog(null, "Debe de estar Seleccionada la Ciudad a Eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error al eliminar datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }   
        };
        jButtonEliminar.addActionListener(oyenteEliminar);
        
        // accion del boton modificar
        ActionListener oyenteActualizar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    //actualizarDatos();
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error en el formato de la población", "Error", JOptionPane.ERROR_MESSAGE);    
                }
                catch(NullPointerException ex)
                {
                     JOptionPane.showMessageDialog(null, "Primero debe estar Seleccionada la Ciudad a Actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error al actualizar datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        jButtonActualizar.addActionListener(oyenteActualizar);
    }
    
    // metodo del mouse
    private void metodoMouse()
    {
        jTableCiudad.addMouseListener(new MouseAdapter()
        {  
            public void mouseClicked(MouseEvent e)
            {
                int i = jTableCiudad.getSelectedRow();
                
                nombreCiudad = (jTableCiudad.getValueAt(i, 0).toString());
            }
        });     
    }

    // metodo del mouse
    private void metodoMouse2()
    {
        jTableCiudad.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                int i = jTableCiudad.getSelectedRow();
                txtNombre.setText(lista.get(i).getNombreCiudad());
                txtPais.setText(lista.get(i).getPaisPertenece());
                txtDistrito.setText(lista.get(i).getDistrito());
                txtPoblacion.setText(String.valueOf(lista.get(i).getPoblacionCiudad()));
            }
        });
    }
    
      /*  metodo crear datos
    public void crearDatos()
    {
        String resp;  
        
        if(!validatTxt())
        {
            return;
        }
        
        String nombreCiudad = txtNombre.getText();
        String paisPertenece = txtPais.getText();
        String distrito = txtDistrito.getText();
        int poblacion = Integer.parseInt(txtPoblacion.getText());

        resp = this.CONTROL.insertar(nombreCiudad,paisPertenece,distrito,poblacion);

        if(resp.equals("OK"))
        {
            if(JOptionPane.showConfirmDialog(null,"Deseas guardar el registro?", "Guardar", JOptionPane.YES_NO_OPTION)==0)
            {
                Ciudad ciudad = new Ciudad(nombreCiudad,paisPertenece,distrito,poblacion);

                lista.add(ciudad);
                mostrarDatos();
                txtNombre.setText("");
                txtPais.setText("");
                txtDistrito.setText("");
                txtPoblacion.setText("");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error! La Población no puede ser Negativa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // metodo mostrar datos
    public void mostrarDatos()
    {
        String matriz[][] = new String[lista.size()][4];
        for (int i = 0; i < lista.size();i++)
        {
            matriz[i][0] = lista.get(i).getNombreCiudad();
            matriz[i][1] = lista.get(i).getPaisPertenece();
            matriz[i][2] = lista.get(i).getDistrito();
            matriz[i][3] = String.valueOf(lista.get(i).getPoblacionCiudad());
        }
        jTableCiudad.setModel(new javax.swing.table.DefaultTableModel(matriz, new String[]{"Nombre Ciudad", "Pais","Distito", "Población"}));
    }
    
    // boton eliminar datos
    public void eliminarDatos()
    {
        if(JOptionPane.showConfirmDialog(this, "Desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION)==0)
        {
            int coord= -999999;
            for (int i = 0; i < lista.size(); i++)
            {
                if(nombreCiudad.equals(lista.get(i).getNombreCiudad()))
                {
                    lista.remove(i);
                    coord = i;
                }
            }
            if(coord != -999999)
            {
                mostrarDatos();
                txtNombre.setText("");
                txtPais.setText("");
                txtDistrito.setText("");
                txtPoblacion.setText("");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No existen Ciudades a Eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
  metodo actualiza datos
    public void actualizarDatos() 
    {
        String resp2;
        
        if(!validatTxt())
        {
            return;
        }
        
        String nuevoNombre = txtNombre.getText();
        String nuevoPais = txtPais.getText();
        String nuevoDistrito = txtDistrito.getText();
        int nuevaPoblacion = Integer.parseInt(txtPoblacion.getText());
        resp2 = this.CONTROL.insertar(nuevoNombre,nuevoPais,nuevoDistrito,nuevaPoblacion);

        if(JOptionPane.showConfirmDialog(null,"Deseas Actualizar el Registro?", "Actualizar", JOptionPane.YES_NO_OPTION)==0)
        {
            if(resp2.equals("OK"))
            {
                for (int i = 0; i < lista.size(); i++) 
                {
                    if (nombreCiudad.equals(lista.get(i).getNombreCiudad())) 
                    {   
                        lista.get(i).setNombreCiudad(nuevoNombre);
                        lista.get(i).setPaisPertenece(nuevoPais);
                        lista.get(i).setDistrito(nuevoDistrito);
                        lista.get(i).setPoblacionCiudad(nuevaPoblacion);
                    }
                    mostrarDatos();
                    txtNombre.setText("");
                    txtPais.setText("");
                    txtDistrito.setText("");
                    txtPoblacion.setText("");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error! La Población no puede ser Negativa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // metodo validar los textos
    public boolean validatTxt()
    {
        if (txtNombre.getText().length() == 0) 
        {
            JOptionPane.showMessageDialog(null, "Debes ingresar un Nombre", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return false;
        }

       if (txtPais.getText().length() == 0) 
       {
            JOptionPane.showMessageDialog(null, "Debes ingresar un País", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtPais.requestFocus();
            return false;
       }

       if (txtDistrito.getText().length() == 0) 
       {
            JOptionPane.showMessageDialog(null, "Debes ingresar un Distrito", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtDistrito.requestFocus();
            return false;
        }

        if (txtPoblacion.getText().length() == 0) 
        {
            JOptionPane.showMessageDialog(null, "Debes ingresar una Población", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtPoblacion.requestFocus();
            return false;
        }
        return true;
    } */
    
    private void pintarImagen(JLabel lbl, String ruta)
    {
        this.imagen = new ImageIcon(ruta);
        this.icono = new ImageIcon(this.imagen.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
        lbl.setIcon(this.icono);
        this.repaint();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jimagen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonCrear = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        txtDistrito = new javax.swing.JTextField();
        txtPoblacion = new javax.swing.JTextField();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCiudad = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRO CIUDADES");
        setResizable(false);

        jLabel1.setText("Nombre Ciudad:");

        jLabel2.setText("Pais Perteneciente:");

        jLabel3.setText("Distrito:");

        jLabel4.setText("Población Ciudad:");

        jButtonCrear.setText("Crear");

        jButtonActualizar.setText("Actualizar");

        jButtonEliminar.setText("Eliminar");

        jTableCiudad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableCiudad);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jButtonEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButtonCrear))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(386, 386, 386)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jButtonActualizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jButtonEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jButtonCrear))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jButtonActualizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 32, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCiudad;
    private javax.swing.JLabel jimagen;
    private javax.swing.JTextField txtDistrito;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtPoblacion;
    // End of variables declaration//GEN-END:variables
}
