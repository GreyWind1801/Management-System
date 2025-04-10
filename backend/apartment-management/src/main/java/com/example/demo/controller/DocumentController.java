package com.example.demo.controller;

import com.example.demo.entities.Document;
import com.example.demo.entities.DocumentType;
import com.example.demo.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<Document> uploadDocument(@RequestBody Document document) {
        return ResponseEntity.ok(documentService.uploadDocument(document));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Document>> getDocumentById(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }   
    
    // Get all documents
    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }
     
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Document>> getDocumentsByType(@PathVariable DocumentType type) {
         return ResponseEntity.ok(documentService.getDocumentsByType(type));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Document>> getDocumentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(documentService.getDocumentsByUserId(userId));
    }
    
    @GetMapping("/apartment/{apartmentId}/type/{type}")
    public ResponseEntity<List<Document>> getDocumentsByApartmentAndType(@PathVariable Long apartmentId,@PathVariable DocumentType type) {
        return ResponseEntity.ok(documentService.getDocumentsByApartmentAndType(apartmentId, type));
    }
    
    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<List<Document>> getDocumentsByApartment(@PathVariable Long apartmentId) {
        return ResponseEntity.ok(documentService.getDocumentsByApartmentId(apartmentId));
    }
    
    @GetMapping("/user/{userId}/apartment/{apartmentId}")
    public ResponseEntity<List<Document>> getDocumentsByUserAndApartment(@PathVariable Long userId,@PathVariable Long apartmentId) {
        List<Document> documents = documentService.getDocumentsByUserAndApartment(userId, apartmentId);
        return ResponseEntity.ok(documents);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Document>> searchDocumentsByName(@RequestParam String name) {
        List<Document> documents = documentService.searchDocumentsByName(name);
        return ResponseEntity.ok(documents);
    } 
}
