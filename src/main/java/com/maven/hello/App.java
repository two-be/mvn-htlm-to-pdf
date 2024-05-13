package com.maven.hello;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) {
        // Path to the HTML file within the resources directory
        String htmlFilePath = "/document.html";

        // Base URL for resource loading (images, CSS, etc.)
        String baseUrl = App.class.getResource("/").toExternalForm();

        // Read HTML file from resources
        String htmlContent;
        try (InputStream is = App.class.getResourceAsStream(htmlFilePath)) {
            htmlContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Output file path
        String outputPath = "output.pdf";

        // Convert HTML to PDF
        try (OutputStream os = new FileOutputStream(outputPath)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(htmlContent, baseUrl);  // Set the base URL to resolve the relative path of CSS
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Hello World!");
    }
}