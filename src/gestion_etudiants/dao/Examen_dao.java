/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_etudiants.dao;

import gestion_etudiants.DB.DatabaseConnection;
import gestion_etudiants.model.Examen;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Examen_dao {

    public boolean ajouter(Examen e) {
        String sql = "INSERT INTO examen (matiere, date) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getMatiere());
            stmt.setString(2, e.getDate());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Examen> getAll() {
        List<Examen> liste = new ArrayList<>();
        String sql = "SELECT * FROM examen";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Examen e = new Examen(
                        rs.getInt("id"),
                        rs.getString("matiere"),
                        rs.getString("date")
                );
                liste.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    public boolean modifier(Examen e) {
        String sql = "UPDATE examen SET matiere = ?, date = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getMatiere());
            stmt.setString(2, e.getDate());
            stmt.setInt(3, e.getId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean supprimer(int id) {
        String sql = "DELETE FROM examen WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Map<String, Integer> getExamensPourCombo() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT id, matiere FROM examen";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                map.put(rs.getString("matiere"), rs.getInt("id"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return map;
    }

    public Map<String, Integer> getExamensPourComboAvecDate() {
    Map<String, Integer> map = new HashMap<>();
    String sql = "SELECT id, matiere, date FROM examen";

    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String label = rs.getString("matiere") + " (" + rs.getString("date") + ")";
            map.put(label, rs.getInt("id"));
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return map;
}


}
