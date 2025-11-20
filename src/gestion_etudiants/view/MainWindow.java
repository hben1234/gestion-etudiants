/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_etudiants.view;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Système de gestion des étudiants");
        ImageIcon windowIcon = new ImageIcon(getClass().getResource("/gestion_etudiants/view/icons/main.png"));
        setIconImage(windowIcon.getImage());

        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

       
        JLabel lblTitre = new JLabel("Tableau de bord");
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        
        ImageIcon iconEtudiants = new ImageIcon(getClass().getResource("/gestion_etudiants/view/icons/etudiants.png"));
        ImageIcon iconExamens = new ImageIcon(getClass().getResource("/gestion_etudiants/view/icons/examens.png"));
        ImageIcon iconNotes = new ImageIcon(getClass().getResource("/gestion_etudiants/view/icons/notes.png"));

        JButton btnEtudiants = new JButton("Étudiants", iconEtudiants);
        JButton btnExamens = new JButton("Examens", iconExamens);
        JButton btnNotes = new JButton("Notes", iconNotes);

        Dimension btnSize = new Dimension(220, 50);

        JButton[] boutons = {btnEtudiants, btnExamens, btnNotes};

        for (JButton b : boutons) {
            b.setPreferredSize(btnSize);
            b.setMinimumSize(btnSize);
            b.setMaximumSize(btnSize);
            b.setBackground(new Color(70, 130, 180));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        }

        
        JPanel panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;

        gbc.gridy = 0;
        panelCenter.add(btnEtudiants, gbc);

        gbc.gridy = 1;
        panelCenter.add(btnExamens, gbc);

        gbc.gridy = 2;
        panelCenter.add(btnNotes, gbc);

        
        add(lblTitre, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);

        
        btnEtudiants.addActionListener(e -> new EtudiantForm().setVisible(true));
        btnExamens.addActionListener(e -> new ExamenForm().setVisible(true));
        btnNotes.addActionListener(e -> new NoteForm().setVisible(true));

    }

}
