package com.pablo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Torneos {
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("torneigs")
    private Map<String, Integer> torneigs;


    public Torneos(String nom, Map<String, Integer> torneigs) {
        this.nom = nom;
        this.torneigs = torneigs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Map<String, Integer> getTorneigs() {
        return torneigs;
    }

    public void setTorneigs(Map<String, Integer> torneigs) {
        this.torneigs = torneigs;
    }

    @Override
    public String toString() {
        return "TorneigGuanyat{" +
                "nom='" + nom + '\'' +
                ", torneigs=" + torneigs +
                '}';
    }
}
