package com.pablo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DatosTenis datosTenis = objectMapper.readValue(new File("src/main/resources/tenistas.json"), DatosTenis.class);

            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("Templates/");
            templateResolver.setSuffix(".html");

            TemplateEngine template = new TemplateEngine();
            template.setTemplateResolver(templateResolver);

            generateIndexHtml(template, datosTenis.getTenistes());

            for (Tenista tenista : datosTenis.getTenistes()) {
                generateTenistaHtml(template, tenista, datosTenis.getTorneigsGuanyats());
            }

            // Generar el archivo RSS con los datos de los tenistas
            generarRSS(datosTenis.getTenistes(), "src/main/resources/Web/rss.xml", "Tenistas", "Última información sobre los tenistas.");

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: ");
            e.printStackTrace();
        }
    }

    private static void generateIndexHtml(TemplateEngine templateEngine, List<Tenista> tenistes) {
        // contexto para la plantilla
        Context context = new Context();
        context.setVariable("tenistes", tenistes);

        //  plantilla y escribir el archivo HTML
        try {
            String htmlContent = templateEngine.process("plantilla_tenistes", context);
            exribirHtml(htmlContent, "src/main/resources/Web/index.html");
        } catch (Exception e) {
            System.err.println("Error al procesar la plantilla de indice: ");
            e.printStackTrace();
        }
    }

    private static void generateTenistaHtml(TemplateEngine templateEngine, Tenista tenista, List<TorneigDetalle> torneigsGuanyats) {
        //  contexto para la plantilla
        Context context = new Context();
        context.setVariable("tenista", tenista);

        //  torneos ganados para el tenista actual
        List<Torneig> torneosGanados = new ArrayList<>();
        for (TorneigDetalle detalle : torneigsGuanyats) {
            if (detalle.getNom().equals(tenista.getNom())) {
                for (Torneig torneo : detalle.getTorneigs().values()) {
                    torneosGanados.add(torneo); // Agregar los torneos a la lista
                }
            }
        }

        context.setVariable("torneigs_guanyats", torneosGanados);

        try {
            String htmlContent = templateEngine.process("plantilla_informacion", context);
            String fileName = tenista.getNom().replace(" ", "_") + ".html"; // Reemplazar espacios por guiones bajos
            exribirHtml(htmlContent, "src/main/resources/Web/" + fileName);
        } catch (Exception e) {
            System.err.println("Error al procesar la plantilla del tenista: ");
            e.printStackTrace();
        }
    }

    private static void exribirHtml(String content, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo HTML: ");
            e.printStackTrace();
        }
    }

    public static void generarRSS(List<Tenista> tenistas, String rutarss, String nombre, String descripcion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutarss))) {
            // Escribir el encabezado del archivo RSS
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<rss version=\"2.0\">\n");
            writer.write("<channel>\n");
            writer.write("<title>" + nombre + "</title>\n");
            writer.write("<link>src/main/resources/Web/index.html</link>\n");
            writer.write("<description>" + descripcion + "</description>\n");

            // Agregar un item por cada tenista
            for (Tenista tenista : tenistas) {
                writer.write("<item>\n");
                writer.write("<title>" + tenista.getNom() + "</title>\n");
                writer.write("<link>src/main/resources/Web/" + tenista.getNom().replaceAll("\\s+", "_") + ".html</link>\n");
                writer.write("<description>Información sobre el tenista " + tenista.getNom() + "</description>\n");
                writer.write("</item>\n");
            }

            // Cerrar las etiquetas RSS
            writer.write("</channel>\n");
            writer.write("</rss>\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error al generar el archivo RSS", e);
        }
    }
}
