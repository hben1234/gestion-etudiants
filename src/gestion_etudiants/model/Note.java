/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_etudiants.model;

public class Note {
    private int id;
    private int idEtudiant;
    private int idExamen;
    private double valeur;

    public Note() {}

    public Note(int id, int idEtudiant, int idExamen, double valeur) {
        this.id = id;
        this.idEtudiant = idEtudiant;
        this.idExamen = idExamen;
        this.valeur = valeur;
    }

    public Note(int idEtudiant, int idExamen, double valeur) {
        this.idEtudiant = idEtudiant;
        this.idExamen = idExamen;
        this.valeur = valeur;
    }

   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdEtudiant() { return idEtudiant; }
    public void setIdEtudiant(int idEtudiant) { this.idEtudiant = idEtudiant; }

    public int getIdExamen() { return idExamen; }
    public void setIdExamen(int idExamen) { this.idExamen = idExamen; }

    public double getValeur() { return valeur; }
    public void setValeur(double valeur) { this.valeur = valeur; }
    
}
