package com.ensa.microservicetransfer.web;

import com.ensa.microservicetransfer.services.TransferService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@RequestMapping("/api/pdftransfer")
@RestController
public class PdfExportController {
    @Autowired
    private TransferService transferService;
    @GetMapping("/pdf/{idTransfer}")
    public void generatePDF(HttpServletResponse response,@PathVariable String idTransfer) throws IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        transferService.export(response,idTransfer);
    }
    @GetMapping("/pdftransferMultiple/{idTransferMultiple}")
    public void generatePDFForTransferMultiple(HttpServletResponse response,@PathVariable String idTransferMultiple) throws IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        transferService.exportForTransferMultiple(response,idTransferMultiple);
    }
}
