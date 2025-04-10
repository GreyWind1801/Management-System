package com.example.demo.services;

import com.example.demo.entities.Document;
import com.example.demo.entities.DocumentType;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Document uploadDocument(Document document);
    Optional<Document> getDocumentById(Long id);
    List<Document> getAllDocuments();
    void deleteDocument(Long id);
    List<Document> getDocumentsByApartmentId(Long apartmentId);
    List<Document> getDocumentsByUserId(Long userId);
    List<Document> getDocumentsByType(DocumentType documentType);
    List<Document> getDocumentsByUserAndApartment(Long userId, Long apartmentId);
    List<Document> searchDocumentsByName(String keyword);
    List<Document> getDocumentsByApartmentAndType(Long apartmentId, DocumentType type);
}
