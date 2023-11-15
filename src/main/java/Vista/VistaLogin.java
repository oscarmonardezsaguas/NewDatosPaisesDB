/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 * @author conta
 */
public class VistaLogin extends javax.swing.JFrame {

Menu menu = new Menu();
    public boolean estado;
    private ImageIcon imagen;
    private Icon icono;
    
    public VistaLogin() {
        initComponents();
         eventosLogin();
        this.pintarImagen(jimagen, "src/main/java/Imagenes/fondo.jpg");
        this.pintarImagen(jlogin, "src/main/java/Imagenes/login2.png");
    }

    
        private void eventosLogin ()
    {
        ActionListener oyenteAceptarLogin = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                boolean resultado;
                String clave = txtContrasena.getText();
                resultado= validarContrasenia(clave);
                
                if(resultado)
                {
                    menu.setVisible(true);
                    VistaLogin.super.setVisible(false);
                }
                else
                {
                    JOptionPane.showConfirmDialog(null, "La contraseña no cumple con los requisitos minimos","Sistema",JOptionPane.WARNING_MESSAGE);
                }
            }
        };
        jButtonAceptar.addActionListener(oyenteAceptarLogin);


        ActionListener oyenteCancelarLogin = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JOptionPane.showMessageDialog(null, "Se canceló el inicio de sesión");
                System.exit(0);
            }
        };
        jButtonCancelar.addActionListener(oyenteCancelarLogin);
    }
    
    public void cerrar()
    {
        int opcion=0;
        opcion = JOptionPane.showConfirmDialog(this,"¿Seguro que desea cerrar el programa?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        switch(opcion)
        {
            case JOptionPane.YES_OPTION:
                System.exit(0);
                break;
            case JOptionPane.NO_OPTION:
                break;
            case JOptionPane.CLOSED_OPTION:
                break;
            default:
                break;
        }
    }
    
        public boolean   validarContrasenia(String contrasena)
    {
        boolean esValida = true;
        if(contrasena.length() < 8)
        {
            esValida = false;
        }
        else
        {
            int caracteresEspeciales = 0;
            for(char caracter:contrasena.toCharArray())
            {
                if(!Character.isLetterOrDigit(caracter))
                {
                    caracteresEspeciales++;
                }
            }
            if(caracteresEspeciales < 2)
            {  
                esValida = false;
            }
        }
        return esValida;
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
        txtUsuario = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jlogin = new javax.swing.JLabel();
        jimagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Usuario:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 160, -1));
        getContentPane().add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 160, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Contraseña:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jButtonAceptar.setText("Aceptar");
        getContentPane().add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        jButtonCancelar.setText("Cancelar");
        getContentPane().add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));
        getContentPane().add(jlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 170, 120));
        getContentPane().add(jimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 300));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jimagen;
    private javax.swing.JLabel jlogin;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
