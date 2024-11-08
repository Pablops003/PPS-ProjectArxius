package com.pablo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Tenista> tenistes = mapper.readValue(new File("tenistas.json"), new TypeReference<List<Tenista>>() {});
            List<Torneos> torneigsGuanyats = mapper.readValue(new File("tenistas.json"), new TypeReference<List<Torneos>>() {});

            System.out.println("Lista de tenistas:");
            for (Tenista tenista : tenistes) {
                System.out.println(tenista);
            }

            System.out.println("\nTorneos ganados:");
            for (Torneos torneigGuanyat : torneigsGuanyats) {
                System.out.println(torneigGuanyat);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

