/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_etudiants.dao;

import gestion_etudiants.model.Etudiant;
import gestion_etudiants.DB.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Etudiant_dao {

    public boolean ajouterEtudiant(Etudiant e) {
        String sql = "INSERT INTO etudiant (nom, prenom) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNom());
            stmt.setString(2, e.getPrenom());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Etudiant> getAllEtudiants() {
        List<Etudiant> liste = new ArrayList<>();
        String sql = "SELECT * FROM etudiant";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Etudiant e = new Etudiant(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom")
                );
                liste.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return liste;
    }

    public boolean modifierEtudiant(Etudiant e) {
        String sql = "UPDATE etudiant SET nom = ?, prenom = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNom());
            stmt.setString(2, e.getPrenom());
            stmt.setInt(3, e.getId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean supprimerEtudiant(int id) {
        String sql = "DELETE FROM etudiant WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Map<String, Integer> getEtudiantsPourCombo() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT id, nom, prenom FROM etudiant";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomComplet = rs.getString("nom") + " " + rs.getString("prenom");
                map.put(nomComplet, rs.getInt("id"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return map;
    }

}
