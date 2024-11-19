package com.pablo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Leer el JSON
            ObjectMapper objectMapper = new ObjectMapper();
            DatosTenis datosTenis = objectMapper.readValue(new File("src/main/resources/tenistas.json"), DatosTenis.class);

            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("templates/");
            templateResolver.setSuffix(".html");

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            // Generar el índice HTML
            generateIndexHtml(templateEngine, datosTenis.getTenistes());

            // Generar HTML para cada tenista
            for (Tenista tenista : datosTenis.getTenistes()) {
                generateTenistaHtml(templateEngine, tenista, datosTenis.getTorneigsGuanyats());
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: ");
            e.printStackTrace();
        }
    }

    private static void generateIndexHtml(TemplateEngine templateEngine, List<Tenista> tenistes) {
        // Crear el contexto para la plantilla
        Context context = new Context();
        context.setVariable("tenistes", tenistes);

        // Procesar la plantilla y escribir el archivo HTML
        try {
            String htmlContent = templateEngine.process("plantilla_tenistes", context);
            writeHtmlToFile(htmlContent, "src/main/resources/Web/index.html");
        } catch (Exception e) {
            System.err.println("Error al procesar la plantilla de índice: ");
            e.printStackTrace();
        }
    }

    private static void generateTenistaHtml(TemplateEngine templateEngine, Tenista tenista, List<TorneigDetalle> torneigsGuanyats) {
        // Crear el contexto para la plantilla
        Context context = new Context();
        context.setVariable("tenista", tenista);

        // Filtrar los torneos ganados para el tenista actual
        List<Torneig> torneosGanados = torneigsGuanyats.stream()
                .filter(t -> t.getNom().equals(tenista.getNom()))
                .flatMap(t -> t.getTorneigs().values().stream()) // Obtener los torneos del mapa
                .toList();

        // Pasar los torneos ganados al contexto
        context.setVariable("torneigs_guanyats", torneosGanados);

        try {
            String htmlContent = templateEngine.process("plantilla_informacion", context);
            String fileName = tenista.getNom().replace(" ", "_") + ".html"; // Reemplazar espacios por guiones bajos
            writeHtmlToFile(htmlContent, "src/main/resources/Web/" + fileName);
        } catch (Exception e) {
            System.err.println("Error al procesar la plantilla del tenista: ");
            e.printStackTrace();
        }
    }

    private static void writeHtmlToFile(String content, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content);
            System.out.println("Archivo creado: " + filePath); // Mensaje de confirmación
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo HTML: ");
            e.printStackTrace();
        }
    }
}