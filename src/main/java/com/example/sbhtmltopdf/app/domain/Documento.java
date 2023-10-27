package com.example.sbhtmltopdf.app.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "DOCUMENTO")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FILENAME")
    private String fileName;

    @Column(name = "PDF")
    @Lob
    private byte[] documentPDF;
}
