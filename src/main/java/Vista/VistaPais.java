package Vista;

import Modelo.Pais;
import Negocio.PaisNegocio;
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
public class VistaPais extends javax.swing.JFrame {

     private final PaisNegocio  CONTROL;
    private int codigo = 0;
    private ImageIcon imagen;
    private Icon icono;
    private int codigoBuscar;
    private String continenteBuscar;
    private String nombrePais;
    
    ArrayList<Pais> lista = new ArrayList<>();   //Lista con los Paises
    ArrayList<Pais> lista2 = new ArrayList<>();  //Lista con los Paises filtrados por Codigo
    ArrayList<Pais> lista3 = new ArrayList<>();  //Lista con los Paises filtrados por Continente

   
    
    public VistaPais() {
        initComponents();
        this.CONTROL = new PaisNegocio();
        eventoBotones();
        metodoMouse();
        metodoMouse2();
        this.pintarImagen(jimagen, "src/main/java/Imagenes/fondo.jpg");
    }

    
      
    private void metodoMouse()
    {
        jTablePais.addMouseListener(new MouseAdapter()
        {     
              public void mouseClicked(MouseEvent e)
              {
                  int i = jTablePais.getSelectedRow();
                  codigo = Integer.parseInt((jTablePais.getValueAt(i, 0).toString()));
                  txtId.setText(String.valueOf(codigo));
                  nombrePais = (jTablePais.getValueAt(i, 1).toString());
              }
        });     
    }
    
    private void metodoMouse2()
    {
        jTablePais.addMouseListener(new MouseAdapter()
        {  
            public void mouseClicked(MouseEvent e)
            {
                int i = jTablePais.getSelectedRow();  
                txtNombre.setText(lista.get(i).getNombre());
                txtCodigo.setText(String.valueOf(lista.get(i).getCodePais()));
                jComboBoxContinente.setSelectedItem(lista.get(i).getContinente());
                txtPoblacion.setText(String.valueOf(lista.get(i).getPoblacion()));
                
            }
        });     
    }
    
    // metodos de los botones
    private void eventoBotones()
    {
        // accion del boton buscar por codigo
        ActionListener oyenteBuscar = new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    buscar(); 
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error en el formato de la Busqueda", "Error", JOptionPane.ERROR_MESSAGE);
                }      
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error al Buscar datos", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }         
        };
        jButtonBuscar.addActionListener(oyenteBuscar);
        
        // accion del boton buscar por continente
        ActionListener oyenteBuscarContinente = new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    buscarContinente(); 
                }    
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error al Buscar datos", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }         
        };
        jButtonBuscarContinente.addActionListener(oyenteBuscarContinente);
        
        // accion del boton agregar registro
        ActionListener oyenteAgregar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    crearDatos( Integer.parseInt(txtCodigo.getText()), txtNombre.getText(), jComboBoxContinente.getSelectedItem().toString() , Integer.parseInt(txtPoblacion.getText()));
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error en el formato de Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error al crear datos", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }          
        };  
        jButtonAgregar.addActionListener(oyenteAgregar); 
        
        // accion del boton eliminar registro
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
        ActionListener oyenteModificar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    modificarDatos();
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error en el formato de Ingreso de Datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error al crear datos", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }   
        };
        jButtonModificar.addActionListener(oyenteModificar);
    }
    
    // evento modifica datos
    public void modificarDatos()
    {
       String resp2;
       if(!validarDatos())
        {
            return;
        }
       int nuevoCodigo = Integer.parseInt(txtCodigo.getText());
       String nuevoNombre = txtNombre.getText();
       String nuevoContinente = jComboBoxContinente.getSelectedItem().toString();
       int nuevaPoblacion = Integer.parseInt(txtPoblacion.getText());
       resp2 = this.CONTROL.insertar(nuevoCodigo,nuevoNombre,nuevoContinente,nuevaPoblacion);
        if(resp2.equals("OK"))
        {
            for(int i = 0; i < lista.size();i++)
            {
                if (nombrePais.equals(lista.get(i).getNombre())) 
                {   
                    lista.get(i).setCodePais(nuevoCodigo);
                    lista.get(i).setNombre(nuevoNombre);
                    lista.get(i).setContinente(nuevoContinente);
                    lista.get(i).setPoblacion(nuevaPoblacion);
                }
                mostrarDatos();
                txtCodigo.setText("");
                txtNombre.setText("");
                txtPoblacion.setText("");       
                txtId.setText("");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error! La Población no puede ser Negativa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // metodo validar datos
    private boolean validarDatos()
    {
        boolean esValida=true;
        if(txtCodigo.getText().length() == 0 || txtCodigo.getText().length() != 3)
        {
            JOptionPane.showMessageDialog(null,"Debes Ingresar un código Valido - Minimo 3 Digitos","Sistema",JOptionPane.WARNING_MESSAGE );
            txtCodigo.requestFocus();
           esValida=false;
        }
        
        if(txtNombre.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null,"Debes Ingresar un Nombre","Sistema",JOptionPane.WARNING_MESSAGE );
            txtNombre.requestFocus();
           esValida=false;
        }
        
        if(txtPoblacion.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null,"Debes Ingresar una Poblacion","Sistema",JOptionPane.WARNING_MESSAGE );
            txtPoblacion.requestFocus();
            esValida=false;
        }
        return esValida;
    }
    
    // metodo buscar por codigo
    public void buscar()
    {
        codigoBuscar = Integer.parseInt(txtBuscar.getText());
        if((txtBuscar.getText().length() == 3))
        {     
            buscar buscar = new buscar();
            for (int x = 0; x < lista.size();x++)
            {
                if(lista.get(x).getCodePais() == codigoBuscar)
                {
                    lista2.add(lista.get(x));
                }
            }
            if(!lista2.isEmpty())
            {
                buscar.setVisible(true);      
                buscar.mostrarDatos2(lista2);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No existe el Pais - Reintente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Debes Ingresar un código de 3 Caracteres","Sistema",JOptionPane.WARNING_MESSAGE);
        }
        txtBuscar.setText("");
        lista2.clear();
    }
    
    // metodo buscar por continente
    public void buscarContinente()
    {
        continenteBuscar = jComboBoxContinenteBuscar.getSelectedItem().toString();
        buscarContinente buscarContinente = new buscarContinente();
        for (int x = 0; x < lista.size();x++)
        {
            if(lista.get(x).getContinente().equals(continenteBuscar))
            {
                lista3.add(lista.get(x));
            }
        }
        if(!lista3.isEmpty())
        {
            buscarContinente.setVisible(true);      
            buscarContinente.mostrarDatos2(lista3);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No existe el Pais - Reintente", "Error", JOptionPane.ERROR_MESSAGE);
        }
        lista3.clear();
    }
    
    // metodo guardar dato en el tabla
    public boolean crearDatos(int codigo, String nombre, String continente, int poblacion)
    {
        String resp;
        boolean esValida=true; 
        if(!validarDatos())
        {
            esValida=false;
            return esValida;
        }
          
        int codigo2 =codigo;
        String nombre2 = nombre;
        String continente2 = continente;
        int poblacion2 =poblacion;
        resp = this.CONTROL.insertar(codigo2,nombre2,continente2,poblacion2);
        if(resp.equals("OK"))
        {
            JOptionPane.showMessageDialog(null, "Datos Ingresados Correctamente","Sistema",JOptionPane.INFORMATION_MESSAGE);
            Pais pais = new Pais(nombre2,continente2,poblacion2, codigo2);
            
            lista.add(pais);
            mostrarDatos();
            txtCodigo.setText("");
            txtNombre.setText("");
            txtPoblacion.setText("");
            esValida=true;
            
        }     else {
            esValida=false;
        }
        return esValida;
    }
    
    // mostrar datos en la tabla
    public void mostrarDatos()
    {
        String matriz[][] = new String[lista.size()][4];
        for (int i = 0; i < lista.size();i++)
        {
            matriz[i][0] = String.valueOf(lista.get(i).getCodePais());
            matriz[i][1] = lista.get(i).getNombre();
            matriz[i][2] = lista.get(i).getContinente();
            matriz[i][3] = String.valueOf(lista.get(i).getPoblacion());
        }
        jTablePais.setModel(new javax.swing.table.DefaultTableModel(matriz, new String[]{"Código", "Nombre", "Continente", "Población"}));
    }
    
    // eliminar datos de la tabla
    public void eliminarDatos()
    {
        if(JOptionPane.showConfirmDialog(this, "Desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION)==0)
        {
            for (int i = 0; i < lista.size(); i++)
            {
                if(codigo == (lista.get(i).getCodePais()))
                {
                    lista.remove(i);
                }
            }
            mostrarDatos();
            txtId.setText("");
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePais = new javax.swing.JTable();
        jButtonAgregar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jButtonBuscarContinente = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxContinenteBuscar = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtPoblacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxContinente = new javax.swing.JComboBox<>();
        jButtonBuscar = new javax.swing.JButton();
        jimagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel1.setText("Registro Poblacional Internacional");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 38, -1, 33));

        jTablePais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTablePais);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 93, 486, 230));

        jButtonAgregar.setText("Agregar");
        getContentPane().add(jButtonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 346, -1, -1));

        jButtonEliminar.setText("Eliminar");
        getContentPane().add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 346, -1, -1));

        jButtonModificar.setText("Modificar");
        getContentPane().add(jButtonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 346, -1, -1));

        jLabel6.setText("Buscar: ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 58, -1, -1));
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 18, 182, -1));

        jButtonBuscarContinente.setText("Buscar");
        getContentPane().add(jButtonBuscarContinente, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 58, -1, -1));

        jLabel8.setText("Código Seleccionado: ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(411, 346, -1, -1));
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 346, 132, -1));

        jLabel2.setText("Código (*)");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 146, -1, -1));
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 146, 280, -1));

        jLabel3.setText("Nombre (*)");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 106, -1, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 106, 280, -1));

        jLabel4.setText("Continente (*)");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 186, -1, -1));

        jComboBoxContinenteBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asia", "America", "Africa", "Antartida", "Europa", "Oceania" }));
        jComboBoxContinenteBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxContinenteBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxContinenteBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 58, 180, -1));

        jLabel5.setText("Población (*)");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 226, -1, -1));
        getContentPane().add(txtPoblacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 226, 280, -1));

        jLabel7.setText("(*) Campo Obligatorio");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 306, 120, 20));

        jLabel9.setText("Buscar: ");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(399, 24, -1, -1));

        jComboBoxContinente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asia", "America", "Africa", "Antartida", "Europa", "Oceania" }));
        jComboBoxContinente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxContinenteActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxContinente, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 186, 280, -1));

        jButtonBuscar.setText("Buscar");
        getContentPane().add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 18, -1, 20));
        getContentPane().add(jimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 906, 390));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxContinenteBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxContinenteBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxContinenteBuscarActionPerformed

    private void jComboBoxContinenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxContinenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxContinenteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonBuscarContinente;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JComboBox<String> jComboBoxContinente;
    private javax.swing.JComboBox<String> jComboBoxContinenteBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePais;
    private javax.swing.JLabel jimagen;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPoblacion;
    // End of variables declaration//GEN-END:variables
}
