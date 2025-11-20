
package gestion_etudiants.view;

import gestion_etudiants.dao.Etudiant_dao;
import gestion_etudiants.model.Etudiant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EtudiantForm extends JFrame{
   private JButton btnAjouter, btnModifier, btnSupprimer;
    private JTable table;
    private DefaultTableModel tableModel;

    private Etudiant_dao dao = new Etudiant_dao();
    private int selectedId = -1;

    public EtudiantForm() {
        setTitle("Gestion des étudiants");
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

        
        tableModel = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom"}, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        
        afficherEtudiants();

      

        btnAjouter.addActionListener(e -> {
            JTextField txtNom = new JTextField();
            JTextField txtPrenom = new JTextField();
            Object[] fields = {
                "Nom :", txtNom,
                "Prénom :", txtPrenom
            };

            int result = JOptionPane.showConfirmDialog(this, fields, "Ajouter un étudiant", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String nom = txtNom.getText().trim();
                String prenom = txtPrenom.getText().trim();

                if (!nom.isEmpty() && !prenom.isEmpty()) {
                    dao.ajouterEtudiant(new Etudiant(nom, prenom));
                    afficherEtudiants();
                } else {
                    JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires.");
                }
            }
        });

        btnModifier.addActionListener(e -> {
            if (selectedId != -1) {
                String nom = (String) tableModel.getValueAt(table.getSelectedRow(), 1);
                String prenom = (String) tableModel.getValueAt(table.getSelectedRow(), 2);

                JTextField txtNom = new JTextField(nom);
                JTextField txtPrenom = new JTextField(prenom);
                Object[] fields = {
                    "Nom :", txtNom,
                    "Prénom :", txtPrenom
                };

                int result = JOptionPane.showConfirmDialog(this, fields, "Modifier l'étudiant", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    dao.modifierEtudiant(new Etudiant(selectedId, txtNom.getText(), txtPrenom.getText()));
                    afficherEtudiants();
                }
            }
        });

        btnSupprimer.addActionListener(e -> {
            if (selectedId != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Confirmer la suppression ?", "Supprimer", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dao.supprimerEtudiant(selectedId);
                    afficherEtudiants();
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

    private void afficherEtudiants() {
        tableModel.setRowCount(0);
        List<Etudiant> liste = dao.getAllEtudiants();
        for (Etudiant e : liste) {
            tableModel.addRow(new Object[]{e.getId(), e.getNom(), e.getPrenom()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EtudiantForm().setVisible(true));
    }
}
