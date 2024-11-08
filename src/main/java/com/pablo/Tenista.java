package com.pablo;

public class Tenista {
    private String nom;
    private int anyNaixement;
    private int edat;

    public Tenista(String nom, int anyNaixement, int edat) {
        this.nom = nom;
        this.anyNaixement = anyNaixement;
        this.edat = edat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnyNaixement() {
        return anyNaixement;
    }

    public void setAnyNaixement(int anyNaixement) {
        this.anyNaixement = anyNaixement;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    @Override
    public String toString() {
        return "Tenista{" +
                "nom='" + nom + '\'' +
                ", anyNaixement=" + anyNaixement +
                ", edat=" + edat +
                '}';
    }
}


