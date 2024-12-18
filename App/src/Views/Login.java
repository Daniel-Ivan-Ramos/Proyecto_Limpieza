/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Views.Admin.MenuAdmin;
import Views.Chief.MenuChief;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import Controller.*;
import Models.*;
import Views.User.MenuUser;
import java.sql.*;

/**
 *
 * @author gerar
 */
public class Login extends javax.swing.JFrame {

    Sesion sesion = Sesion.getInstance();
    UsuarioDAO userDAO = new UsuarioDAO();
    EmpleadoDAO empDAO = new EmpleadoDAO();

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        actualizarHoraDinamicamente();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title1 = new javax.swing.JLabel();
        subtitle1 = new javax.swing.JLabel();
        dateTime = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        userTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title1.setFont(new java.awt.Font("Bahnschrift", 1, 48)); // NOI18N
        title1.setText("ACTIVIDADES DE LIMPIEZA");

        subtitle1.setFont(new java.awt.Font("Poppins Medium", 1, 24)); // NOI18N
        subtitle1.setText("Ingresa al sistema con tus credenciales");

        dateTime.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        userLabel.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        userLabel.setText("Usuario:");

        userTextField.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N

        passwordLabel.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        passwordLabel.setText("Contraseña:");
        passwordLabel.setToolTipText("");

        loginButton.setBackground(new java.awt.Color(20, 145, 155));
        loginButton.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        loginButton.setText("INICIAR SESIÓN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(481, 481, 481)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(459, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(title1)
                .addContainerGap(247, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(subtitle1)
                .addGap(290, 290, 290))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(userLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(passwordLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(dateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(579, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(title1)
                .addGap(18, 18, 18)
                .addComponent(subtitle1)
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(dateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        String user = "";
        String password = "";
        boolean valido = false;
        boolean admin = false;

        try {
            user = userTextField.getText().trim();
            if (user.isBlank()) {
                JOptionPane.showMessageDialog(null, "¡Debes ingresar el usuario!", "Error.", JOptionPane.ERROR_MESSAGE);
                return;
            }

            password = passwordField.getText().trim();
            if (password.isBlank()) {
                JOptionPane.showMessageDialog(null, "¡Debes ingresar la contraseña!", "Error.", JOptionPane.ERROR_MESSAGE);
                return;
            }

            valido = userDAO.iniciarSesion(user, password);
            admin = userDAO.esAdmin();
            Usuario usuario = sesion.getUsuarioActivo();
            Empleado empleado = empDAO.obtenerEmpleadoPorIdUsuario(usuario.getId_usuario());
            
            if (valido && admin) { // Verificar si está registrado y es administrador
                this.setVisible(false);
                MenuAdmin contenido = new MenuAdmin();
                contenido.setVisible(true);
            } else if(valido && !admin && empleado.isEsJefeCuadrilla()){ // Verificar si está registrado, no es administrador y es jefe de cuadrilla
                this.setVisible(false);
                MenuChief contenido =new MenuChief();
                contenido.setVisible(true);
            
            }else if(valido && !admin && !empleado.isEsJefeCuadrilla()){ // Verificar si está registrado, no es administrador y es jefe de cuadrilla
                this.setVisible(false);
                MenuUser contenido =new MenuUser();
                contenido.setVisible(true);
            
            }else {
                JOptionPane.showMessageDialog(null, "¡Las credenciales no son correctas!", "Credenciales Incorrectas", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en algún formato de los campos.");
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Actualizar hora y fecha
    private void actualizarHoraDinamicamente() {
        Timer timer = new Timer(1000, e -> { // Se ejecuta cada 1000 ms (1 segundo)
            LocalDateTime fechaYHoraActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            dateTime.setText(fechaYHoraActual.format(formato));
        });
        timer.start(); // Inicia el temporizador
    }

    private void limpiarCampos() {
        userTextField.setText("");
        passwordField.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateTime;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel subtitle1;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userTextField;
    // End of variables declaration//GEN-END:variables
}
