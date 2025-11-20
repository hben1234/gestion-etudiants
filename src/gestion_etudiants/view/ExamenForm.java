/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_etudiants.view;


import gestion_etudiants.dao.Examen_dao;
import gestion_etudiants.model.Examen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ExamenForm extends JFrame {
    private JButton btnAjouter, btnModifier, btnSupprimer;
    private JTable table;
    private DefaultTableModel tableModel;

    private Examen_dao dao = new Examen_dao();
    private int selectedId = -1;

    public ExamenForm() {
        setTitle("Gestion des Examens");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel panelButtons = new JPanel(new FlowLayout());

        btnAjouter = new JButton("Ajouter");
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");

        panelButtons.add(btnAjouter);
        panelButtons.add(btnModifier);
        panelButtons.add(btnSupprimer);

        add(panelButtons, BorderLayout.SOUTH);

        
        tableModel = new DefaultTableModel(new String[]{"ID", "Matière", "Date"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

       
        afficherExamens();

        
        btnAjouter.addActionListener(e -> {
            JTextField txtMatiere = new JTextField();
            JTextField txtDate = new JTextField(); 

            Object[] fields = {
                "Matière :", txtMatiere,
                "Date (yyyy-mm-dd) :", txtDate
            };

            int result = JOptionPane.showConfirmDialog(this, fields, "Ajouter un examen", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String matiere = txtMatiere.getText().trim();
                String date = txtDate.getText().trim();

                if (!matiere.isEmpty() && !date.isEmpty()) {
                    dao.ajouter(new Examen(matiere, date));
                    afficherExamens();
                } else {
                    JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires.");
                }
            }
        });

        btnModifier.addActionListener(e -> {
            if (selectedId != -1) {
                String matiere = (String) tableModel.getValueAt(table.getSelectedRow(), 1);
                String date = (String) tableModel.getValueAt(table.getSelectedRow(), 2);

                JTextField txtMatiere = new JTextField(matiere);
                JTextField txtDate = new JTextField(date);

                Object[] fields = {
                    "Matière :", txtMatiere,
                    "Date (yyyy-mm-dd) :", txtDate
                };

                int result = JOptionPane.showConfirmDialog(this, fields, "Modifier l'examen", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    dao.modifier(new Examen(selectedId, txtMatiere.getText(), txtDate.getText()));
                    afficherExamens();
                }
            }
        });

        btnSupprimer.addActionListener(e -> {
            if (selectedId != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Confirmer la suppression ?", "Supprimer", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dao.supprimer(selectedId);
                    afficherExamens();
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int index = table.getSelectedRow();
            if (index != -1) {
                selectedId = (int) tableModel.getValueAt(index, 0);
            }
        });
    }

    private void afficherExamens() {
        tableModel.setRowCount(0);
        List<Examen> examens = dao.getAll();
        for (Examen e : examens) {
            tableModel.addRow(new Object[]{e.getId(), e.getMatiere(), e.getDate()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExamenForm().setVisible(true));
    }
}
