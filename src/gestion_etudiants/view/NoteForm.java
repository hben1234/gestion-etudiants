/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_etudiants.view;

import gestion_etudiants.dao.Etudiant_dao;
import gestion_etudiants.dao.Examen_dao;
import gestion_etudiants.dao.Note_dao;
import gestion_etudiants.model.Note;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class NoteForm extends JFrame {

    private JButton btnAjouter, btnModifier, btnSupprimer;
    private JTable table;
    private DefaultTableModel tableModel;

    private Note_dao noteDAO = new Note_dao();
    private Etudiant_dao etudiantDAO = new Etudiant_dao();
    private Examen_dao examenDAO = new Examen_dao();

    private Map<String, Integer> mapEtudiants;
    private Map<String, Integer> mapExamens;

    public NoteForm() {
        setTitle("Gestion des Notes");
        setSize(700, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Matière", "Date", "Note"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        JPanel panelButtons = new JPanel(new FlowLayout());
        btnAjouter = new JButton("Ajouter");
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");
        panelButtons.add(btnAjouter);
        panelButtons.add(btnModifier);
        panelButtons.add(btnSupprimer);
        add(panelButtons, BorderLayout.SOUTH);

        
        btnAjouter.addActionListener(e -> {
            mapEtudiants = etudiantDAO.getEtudiantsPourCombo();
            mapExamens = examenDAO.getExamensPourComboAvecDate();

            JComboBox<String> etudCombo = new JComboBox<>(mapEtudiants.keySet().toArray(new String[0]));
            JComboBox<String> examCombo = new JComboBox<>(mapExamens.keySet().toArray(new String[0]));
            JTextField txtNote = new JTextField();

            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("Étudiant :"));
            panel.add(etudCombo);
            panel.add(new JLabel("Examen :"));
            panel.add(examCombo);
            panel.add(new JLabel("Note :"));
            panel.add(txtNote);

            int res = JOptionPane.showConfirmDialog(this, panel, "Ajouter une note", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                try {
                    int idEtud = mapEtudiants.get(etudCombo.getSelectedItem().toString());
                    int idExam = mapExamens.get(examCombo.getSelectedItem().toString());
                    double valeur = Double.parseDouble(txtNote.getText());
                    noteDAO.ajouter(new Note(idEtud, idExam, valeur));
                    rafraichir();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
                }
            }
        });

        btnModifier.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Sélectionnez une note à modifier.");
                return;
            }
            String ancienneNote = tableModel.getValueAt(row, 5).toString();

            String nouvelle = JOptionPane.showInputDialog(this, "Nouvelle note :", ancienneNote);
            if (nouvelle != null) {
                try {
                    int noteId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                    double val = Double.parseDouble(nouvelle);
                    noteDAO.modifierNoteValeur(noteId, val);
                    rafraichir();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Note invalide !");
                }
            }
        });

        btnSupprimer.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Sélectionnez une note à supprimer.");
                return;
            }
            int noteId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
            int confirm = JOptionPane.showConfirmDialog(this, "Supprimer cette note ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                noteDAO.supprimer(noteId);
                rafraichir();
            }
        });

        rafraichir();
    }

    private void rafraichir() {
        tableModel.setRowCount(0);
        List<String[]> notes = noteDAO.getAllDetails();
        for (String[] row : notes) {
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NoteForm().setVisible(true));
    }
}
