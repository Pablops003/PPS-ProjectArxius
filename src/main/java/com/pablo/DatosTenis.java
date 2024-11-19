package com.pablo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DatosTenis {
    @JsonProperty("tenistes")
    private List<Tenista> tenistes;

    @JsonProperty("torneigs_guanyats")
    private List<TorneigDetalle> torneigsGuanyats;

    // Getters y Setters
    public List<Tenista> getTenistes() {
        return tenistes;
    }

    public void setTenistes(List<Tenista> tenistes) {
        this.tenistes = tenistes;
    }

    public List<TorneigDetalle> getTorneigsGuanyats() {
        return torneigsGuanyats;
    }

    public void setTorneigsGuanyats(List<TorneigDetalle> torneigsGuanyats) {
        this.torneigsGuanyats = torneigsGuanyats;
    }
}