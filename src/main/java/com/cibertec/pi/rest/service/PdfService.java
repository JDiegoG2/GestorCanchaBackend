package com.cibertec.pi.rest.service;

import com.cibertec.pi.rest.response.ReservaResponse;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    private static final String PDF_DIRECTORY = "C:/Users/ROY/Documents/pdf/";// Cambia esta ruta por la ruta donde deseas almacenar los PDFs

    public byte[] generatePdfTicket(ReservaResponse reserva) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Ticket de Reserva"));
        document.add(new Paragraph("ID Reserva: " + reserva.getId()));
        document.add(new Paragraph("Cancha: " + reserva.getCancha()));
        document.add(new Paragraph("Fecha: " + reserva.getFechaReserva()));
        document.add(new Paragraph("Hora: " + reserva.getHoraReserva()));
        document.add(new Paragraph("Importe: S/" + reserva.getImporte()));

        document.close();

        return baos.toByteArray();
    }

    public String savePdfTicket(ReservaResponse reserva) throws IOException {
        byte[] pdfBytes = generatePdfTicket(reserva);
        String pdfFileName = "reserva_" + reserva.getId() + ".pdf";
        String pdfFilePath = PDF_DIRECTORY + pdfFileName;

        try (FileOutputStream fos = new FileOutputStream(pdfFilePath)) {
            fos.write(pdfBytes);
        }

        return pdfFilePath;
    }

    public String getPdfUrl(Long reservaId) {
        String pdfFileName = "reserva_" + reservaId + ".pdf";
        return "/pdfs/" + pdfFileName;
    }
}
