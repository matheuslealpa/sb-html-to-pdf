package com.example.sbhtmltopdf.app.repository;

import com.example.sbhtmltopdf.app.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long>,
                                             JpaSpecificationExecutor<Documento> {
}
