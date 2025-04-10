package com.example.demo.repositories;

import com.example.demo.entities.Document;
import com.example.demo.entities.DocumentType;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByApartmentApartmentId(Long apartmentId);
    List<Document> findByUserUserId(Long userId);
    List<Document> findByDocumentType(DocumentType documentType);
    List<Document> findByUserUserIdAndApartmentApartmentId(Long userId, Long apartmentId);
    List<Document> findByDocumentNameContainingIgnoreCase(String keyword);
    List<Document> findByApartmentApartmentIdAndDocumentType(Long apartmentId, String documentType);
    
    @Query("SELECT d.documentType, COUNT(d) FROM Document d GROUP BY d.documentType")
    List<Object[]> countDocumentsByType();

}
