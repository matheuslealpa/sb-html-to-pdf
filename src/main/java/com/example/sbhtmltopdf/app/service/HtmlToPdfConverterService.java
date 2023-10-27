package com.example.sbhtmltopdf.app.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Serviço para a conversão de HTML em PDF
 *
 * @version 1.0
 * @since 13/05/2023
 * @author Matheus Leal
 * @link https://github.com/matheuslealpa/sb-html-to-pdf
 */
@Service
@Slf4j
public class HtmlToPdfConverterService {

    /**
     * Converte o conteúdo HTML fornecido em um arquivo PDF
     *
     * @param htmlBytes o conteúdo HTML a ser convertido em um arquivo PDF, codificado em base64
     * @return o conteúdo do arquivo PDF resultante, codificado em base64
     */
    @SneakyThrows
    public byte[] exec(byte[] htmlBytes) {
        log.info("Inicializando service que converte html em pdf");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(htmlBytes);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            Document document = Jsoup.parse(byteArrayInputStream, "UTF-8", "");
            document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setInteractive(false);

            renderer.setDocumentFromString(document.html());
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
    }
}
