package com.pablo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Torneig {
    @JsonProperty("titol")
    private String titol;

    @JsonProperty("foto")
    private String foto;

    // Getters y Setters
    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}