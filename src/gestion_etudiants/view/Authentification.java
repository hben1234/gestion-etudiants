/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_etudiants.view;
import javax.swing.*;
import java.awt.*;
import gestion_etudiants.dao.UtilisateurDAO;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Authentification extends JFrame {

     private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnConnexion;

    public Authentification() {
        setTitle("Connexion à l'application");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        
        JLabel lblTitre = new JLabel("Bienvenue dans le système de gestion des étudiants");
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        
        JLabel lblEmail = new JLabel("Email institutionnel :");
        txtEmail = new JTextField(20);

        JLabel lblPassword = new JLabel("Mot de passe :");
        txtPassword = new JPasswordField(20);

        
        btnConnexion = new JButton("Connexion");
        btnConnexion.setBackground(new Color(70, 130, 180));
        btnConnexion.setForeground(Color.WHITE);
        btnConnexion.setFocusPainted(false);
        btnConnexion.setFont(new Font("Segoe UI", Font.BOLD, 14));

       
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(lblEmail, gbc);
        gbc.gridx = 1;
        panelForm.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(lblPassword, gbc);
        gbc.gridx = 1;
        panelForm.add(txtPassword, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelForm.add(btnConnexion, gbc);

        
        setLayout(new BorderLayout());
        add(lblTitre, BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);

       
        btnConnexion.addActionListener(e -> {
            String email = txtEmail.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            UtilisateurDAO dao = new UtilisateurDAO();
            boolean isValid = dao.verifierConnexion(email, password);

            if (isValid) {
                JOptionPane.showMessageDialog(this, " Connexion réussie !");
                new gestion_etudiants.view.MainWindow().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, " Email ou mot de passe incorrect !");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Authentification().setVisible(true));
    }
}
