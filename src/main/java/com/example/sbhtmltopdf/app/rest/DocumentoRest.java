package com.example.sbhtmltopdf.app.rest;

import com.example.sbhtmltopdf.app.domain.Documento;
import com.example.sbhtmltopdf.app.repository.DocumentoRepository;
import com.example.sbhtmltopdf.app.service.HtmlToPdfConverterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/documento")
public class DocumentoRest {

    private final DocumentoRepository documentoRepository;
    private final HtmlToPdfConverterService htmlToPDF;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Documento> insert(@RequestBody Documento resource){
        if (Objects.nonNull(resource.getDocumentPDF())){
            resource.setDocumentPDF(htmlToPDF.exec(resource.getDocumentPDF()));
        }
        return ResponseEntity.ok(documentoRepository.save(resource));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<Documento>> findALl(Pageable pageable){
        return ResponseEntity.ok(documentoRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Documento>> findById(@PathVariable Long id){
        if (!documentoRepository.existsById(id)) throw new EntityNotFoundException("Entidade não encontrada");
        return ResponseEntity.ok(documentoRepository.findById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Documento> update(@PathVariable Long id, @RequestBody Documento resource){
        if (!documentoRepository.existsById(id)) throw new EntityNotFoundException("entidade não encontrada");
        if (Objects.nonNull(resource.getDocumentPDF())){
            resource.setDocumentPDF(htmlToPDF.exec(resource.getDocumentPDF()));
        }
        return ResponseEntity.ok(documentoRepository.save(resource));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Documento resource){
        if (!documentoRepository.existsById(id)) throw new EntityNotFoundException("entidade não encontrada");
        documentoRepository.delete(resource);
    }

}
