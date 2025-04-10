package com.example.demo.services.impl;

import com.example.demo.entities.Document;
import com.example.demo.entities.DocumentType;
import com.example.demo.repositories.DocumentRepository;
import com.example.demo.services.DocumentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document uploadDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
    
    @Override
    public List<Document> getDocumentsByApartmentId(Long apartmentId) {
        return documentRepository.findByApartmentApartmentId(apartmentId);
    }
    
    @Override
    public List<Document> getDocumentsByType(DocumentType documentType) {
        return documentRepository.findByDocumentType(documentType);
    }
    
    @Override
    public List<Document> getDocumentsByUserAndApartment(Long userId, Long apartmentId) {
        return documentRepository.findByUserUserIdAndApartmentApartmentId(userId, apartmentId);
    }
    
    @Override
    public List<Document> getDocumentsByUserId(Long userId) {
        return documentRepository.findByUserUserId(userId);
    }
    
    @Override
    public List<Document> searchDocumentsByName(String keyword) {
        return documentRepository.findByDocumentNameContainingIgnoreCase(keyword);
    }
    
    @Override
    public List<Document> getDocumentsByApartmentAndType(Long apartmentId, DocumentType type) {
        return documentRepository.findByApartmentApartmentIdAndDocumentType(apartmentId, type.name());
    }
}
