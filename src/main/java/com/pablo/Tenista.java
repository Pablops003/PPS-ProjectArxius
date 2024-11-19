package com.pablo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Tenista {
    @JsonProperty("nom")
    private String nom;

    @JsonProperty("any_naixement")
    private int anyNaixement;

    @JsonProperty("edat")
    private int edat;

    @JsonProperty("foto")
    private String foto;

    // Getters y Setters
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}