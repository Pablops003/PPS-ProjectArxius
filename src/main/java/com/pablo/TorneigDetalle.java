package com.pablo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class TorneigDetalle {
    @JsonProperty("nom")
    private String nom;

    @JsonProperty("torneigs")
    private Map<String, Torneig> torneigs;

    // Getters y Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Map<String, Torneig> getTorneigs() {
        return torneigs;
    }

    public void setTorneigs(Map<String, Torneig> torneigs) {
        this.torneigs = torneigs;
    }
}