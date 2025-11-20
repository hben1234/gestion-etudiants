/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_etudiants.dao;

import gestion_etudiants.DB.DatabaseConnection;
import gestion_etudiants.model.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Note_dao {

    public boolean ajouter(Note n) {
        String sql = "INSERT INTO note (id_etudiant, id_examen, note_obtenu) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, n.getIdEtudiant());
            stmt.setInt(2, n.getIdExamen());
            stmt.setDouble(3, n.getValeur());

            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    

    public boolean supprimer(int id) {
        String sql = "DELETE FROM note WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    
    public List<String[]> getAllDetails() {
    List<String[]> liste = new ArrayList<>();
    String sql = """
        SELECT n.id, e.nom, e.prenom, ex.matiere, ex.date, n.note_obtenu
        FROM note n
        JOIN etudiant e ON n.id_etudiant = e.id
        JOIN examen ex ON n.id_examen = ex.id
    """;

    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String[] row = new String[]{
                String.valueOf(rs.getInt("id")),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("matiere"),
                rs.getString("date"), 
                String.valueOf(rs.getDouble("note_obtenu"))
            };
            liste.add(row);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return liste;
}


   
    
    public void modifierNoteValeur(int idNote, double note_nouvelle) {
    String sql = "UPDATE note SET note_obtenu = ? WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDouble(1, note_nouvelle);
        stmt.setInt(2, idNote);
        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
  }  
}




