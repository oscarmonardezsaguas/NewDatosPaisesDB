/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Pais;
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
import javax.swing.table.TableRowSorter;

/**
 *
 * @author conta
 */
public class VistaPais extends javax.swing.JFrame {

    private final PaisNegocio  CONTROL;
    private int codigo = 0;
    private ImageIcon imagen;
    private Icon icono;
    private String codigoBuscar;
    private String continenteBuscar;
    private String nombrePais;
    
    ArrayList<Pais> lista = new ArrayList<>();   //Lista con los Paises
   
    
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
        tablaListado.addMouseListener(new MouseAdapter()
        {     
              public void mouseClicked(MouseEvent e)
              {
                  int i = tablaListado.getSelectedRow();
                  codigo = Integer.parseInt((tablaListado.getValueAt(i, 0).toString()));
                  txtId.setText(String.valueOf(codigo));
                  nombrePais = (tablaListado.getValueAt(i, 1).toString());
              }
        });     
    }
    
    private void metodoMouse2()
    {
        tablaListado.addMouseListener(new MouseAdapter()
        {  
            public void mouseClicked(MouseEvent e)
            {
                int i = tablaListado.getSelectedRow();  
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
                    String textobuscar = txtBuscar.getText();
                    listar(textobuscar);                
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
                    //buscarContinente(); 
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
                   guardarRegistro( txtCodigo.getText(), txtNombre.getText(), jComboBoxContinente.getSelectedItem().toString() , Integer.parseInt(txtPoblacion.getText()));
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
        ActionListener oyenteModificar = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                   // modificarDatos();
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

    
    // metodo buscar por continente

    
    // metodo guardar datos
    public void guardarRegistro(String codigo, String nombre, String continente, int poblacion){
        String resp;
        resp=this.CONTROL.insertar(codigo, nombre, continente, poblacion);
        if(resp.equals("OK")){                
            JOptionPane.showMessageDialog(null, "ATENCION, registro almacenado correctamente...", "Error", JOptionPane.ERROR_MESSAGE);
            this.limpiar();         
        }else{
            JOptionPane.showMessageDialog(null, "ATENCION, ocurrio un error ...", "Error", JOptionPane.ERROR_MESSAGE);
        }    
    }
 
    
    // mostrar datos en la tabla
  
    
    // eliminar datos de la tabla
    
    private void listar(String texto){
        tablaListado.setModel(this.CONTROL.listar(texto));          
        TableRowSorter orden= new TableRowSorter(tablaListado.getModel());
        tablaListado.setRowSorter(orden);
    }
    

    
    private void pintarImagen(JLabel lbl, String ruta)
    {
        this.imagen = new ImageIcon(ruta);
        this.icono = new ImageIcon(this.imagen.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
        lbl.setIcon(this.icono);
        this.repaint();
    }

    
     private void limpiar(){
        txtCodigo.setText("");
        txtNombre.setText("");       
        txtId.setText("");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jimagen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jComboBoxContinente = new javax.swing.JComboBox<>();
        txtPoblacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButtonAgregar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxContinenteBuscar = new javax.swing.JComboBox<>();
        jButtonBuscar = new javax.swing.JButton();
        jButtonBuscarContinente = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRO PAISES");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel1.setText("Registro Poblacional Internacional");

        jLabel3.setText("Nombre (*)");

        jLabel2.setText("Código (*)");

        jLabel4.setText("Continente (*)");

        jComboBoxContinente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asia", "America", "Africa", "Antartida", "Europa", "Oceania" }));
        jComboBoxContinente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxContinenteActionPerformed(evt);
            }
        });

        jLabel7.setText("(*) Campo Obligatorio");

        jButtonAgregar.setText("Agregar");

        jButtonEliminar.setText("Eliminar");

        jButtonModificar.setText("Modificar");

        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaListado);

        jLabel9.setText("Buscar: ");

        jLabel6.setText("Buscar: ");

        jComboBoxContinenteBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asia", "America", "Africa", "Antartida", "Europa", "Oceania" }));
        jComboBoxContinenteBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxContinenteBuscarActionPerformed(evt);
            }
        });

        jButtonBuscar.setText("Buscar");

        jButtonBuscarContinente.setText("Buscar");

        jLabel8.setText("Código Seleccionado: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jimagen, javax.swing.GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel1)
                            .addGap(96, 96, 96)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel9))
                                .addComponent(jLabel6))
                            .addGap(7, 7, 7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxContinenteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(28, 28, 28)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonBuscar)
                                .addComponent(jButtonBuscarContinente)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(20, 20, 20)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(27, 27, 27)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(5, 5, 5)
                                    .addComponent(jComboBoxContinente, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(81, 81, 81)
                                    .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(22, 22, 22)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jButtonAgregar)
                            .addGap(22, 22, 22)
                            .addComponent(jButtonEliminar)
                            .addGap(33, 33, 33)
                            .addComponent(jButtonModificar)
                            .addGap(85, 85, 85)
                            .addComponent(jLabel8)
                            .addGap(10, 10, 10)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jimagen, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel6))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBoxContinenteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jButtonBuscarContinente)))
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jComboBoxContinente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(58, 58, 58)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonAgregar)
                        .addComponent(jButtonEliminar)
                        .addComponent(jButtonModificar)
                        .addComponent(jLabel8)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxContinenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxContinenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxContinenteActionPerformed

    private void jComboBoxContinenteBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxContinenteBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxContinenteBuscarActionPerformed

 

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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jimagen;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPoblacion;
    // End of variables declaration//GEN-END:variables
}
