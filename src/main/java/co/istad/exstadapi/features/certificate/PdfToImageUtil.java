package co.istad.exstadapi.features.certificate;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class PdfToImageUtil {

    public static File convertPdfToPng(File pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300); // Render first page at 300 DPI

            File outputFile = new File(pdfFile.getParent(), pdfFile.getName().replace(".pdf", ".png"));
            ImageIO.write(image, "png", outputFile);

            return outputFile;
        }
    }

}

