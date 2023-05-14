package com.example.sbhtmltopdf.app.rest;

import com.example.sbhtmltopdf.app.domain.Documento;
import com.example.sbhtmltopdf.app.repository.DocumentoRepository;
import com.example.sbhtmltopdf.app.service.HtmlToPdfConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/documento")
public class DocumentoRest {
    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private HtmlToPdfConverterService htmlToPDF;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Documento> insert(@RequestBody Documento resource){
        if (resource.getDocumentPDF() !=null){
            resource.setDocumentPDF(htmlToPDF.exec(resource.getDocumentPDF()));
        }
        return ResponseEntity.ok(documentoRepository.save(resource));
    }
}
