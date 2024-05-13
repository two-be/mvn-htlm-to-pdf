package com.maven.hello;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class App {
    public static void main(String[] args) {
        // Assuming the HTML and CSS files are in src/main/resources
        String baseUrl = App.class.getResource("/").toExternalForm();

        String htmlContent = """
            <html>
                <head>
                    <link rel="stylesheet" type="text/css" href="style.css"></link>
                    <title>Test PDF</title>
                </head>
                <body>
                    <h1>Hello, OpenHTMLtoPDF!</h1>
                    <p>This is a PDF document generated from HTML with external CSS.</p>
                </body>
            </html>
            """;

        // Output file path
        String outputPath = "output-css.pdf";

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