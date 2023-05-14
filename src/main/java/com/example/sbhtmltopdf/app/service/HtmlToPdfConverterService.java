package com.example.sbhtmltopdf.app.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class HtmlToPdfConverterService {

    public byte[] exec(byte[] htmlBytes) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
