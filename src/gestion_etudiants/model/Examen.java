/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_etudiants.model;

public class Examen {
    private int id;
    private String matiere;
    private String date; 

    public Examen() {}

    public Examen(int id, String matiere, String date) {
        this.id = id;
        this.matiere = matiere;
        this.date = date;
    }

    public Examen(String matiere, String date) {
        this.matiere = matiere;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMatiere() { return matiere; }
    public void setMatiere(String matiere) { this.matiere = matiere; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

}
