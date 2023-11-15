
package Vista;

import Modelo.Ciudad;
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
                    crearDatos();
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
                    eliminarDatos();
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
                    actualizarDatos();
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
    
    // metodo crear datos
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
    
    // metodo actualiza datos
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
    }
    
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        txtDistrito = new javax.swing.JTextField();
        txtPoblacion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCiudad = new javax.swing.JTable();
        jButtonCrear = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jimagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre Ciudad:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 33, -1, -1));

        jLabel2.setText("Pais Perteneciente:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 71, -1, -1));

        jLabel3.setText("Distrito:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 111, -1, -1));

        jLabel4.setText("Población Ciudad:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 152, -1, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 218, -1));
        getContentPane().add(txtPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 68, 218, -1));
        getContentPane().add(txtDistrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 108, 218, -1));
        getContentPane().add(txtPoblacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 152, 218, -1));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(386, 21, 518, 185));

        jButtonCrear.setText("Crear");
        getContentPane().add(jButtonCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 192, -1, -1));

        jButtonActualizar.setText("Actualizar");
        getContentPane().add(jButtonActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 192, -1, -1));

        jButtonEliminar.setText("Eliminar");
        getContentPane().add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 192, -1, -1));
        getContentPane().add(jimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 236));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


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
