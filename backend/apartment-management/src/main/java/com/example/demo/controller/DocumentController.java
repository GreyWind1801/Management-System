package com.example.demo.controller;

import com.example.demo.entities.Apartment;
import com.example.demo.entities.Document;
import com.example.demo.entities.DocumentType;
import com.example.demo.entities.User;
import com.example.demo.services.DocumentService;
import com.example.demo.services.FileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private FileStorageService fileStorageService;

    /*@PostMapping
    public ResponseEntity<Document> uploadDocument(@RequestBody Document document) {
        return ResponseEntity.ok(documentService.uploadDocument(document));
    }*/
    
    @PostMapping
    public ResponseEntity<?> uploadDocument(
        @RequestPart("user") String userJson,
        @RequestPart("apartment") String apartmentJson,
        @RequestPart("documentName") String documentName,
        @RequestPart("description") String description,
        @RequestPart("documentType") String documentType,
        @RequestPart("isPublic") Boolean isPublic,
        @RequestPart("file") MultipartFile file
    ) {
        try {
            // 1. Parse JSON strings into objects
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(userJson, User.class);
            Apartment apartment = mapper.readValue(apartmentJson, Apartment.class);

            // 2. Upload file and get URL
            String fileUrl = fileStorageService.saveFile(file);

            // 3. Prepare Document entity
            Document doc = new Document();
            doc.setUser(user);
            doc.setApartment(apartment);
            doc.setDocumentName(documentName);
            doc.setDescription(description);
            doc.setDocumentType(DocumentType.valueOf(documentType));
            doc.setPublic(isPublic);
            doc.setFileSize(file.getSize());
            doc.setFileUrl(fileUrl);

            // 4. Save document record
            documentService.uploadDocument(doc);

            return ResponseEntity.ok("Document uploaded!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Upload failed: " + e.getMessage());
        }
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
