package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;
    
    @Column(length = 500)
    private String description;
    
    @Column
    private Long fileSize; // in bytes
    
    @Column(nullable = false)
    private boolean isPublic = false;

    @Column(nullable = false, length = 255)
    private String documentName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType; // Lease Agreement, ID Proof, Utility Bill

    @Column(nullable = false)
    private LocalDateTime uploadDate = LocalDateTime.now();

    @Column(nullable = false, columnDefinition = "TEXT")
    private String fileUrl;

    // Constructors
    public Document() {}

    public Document(User user, Apartment apartment, String documentName, DocumentType documentType, String fileUrl) {
        this.user = user;
        this.apartment = apartment;
        this.documentName = documentName;
        this.documentType = documentType;
        this.fileUrl = fileUrl;
        this.uploadDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Apartment getApartment() { return apartment; }
    public void setApartment(Apartment apartment) { this.apartment = apartment; }

    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }

    public DocumentType getDocumentType() { return documentType; }
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    public LocalDateTime getUploadDate() { return uploadDate; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	public Long getFileSize() {return fileSize;}
	public void setFileSize(Long fileSize) {this.fileSize = fileSize;}

	public boolean isPublic() {return isPublic;}
	public void setPublic(boolean isPublic) {this.isPublic = isPublic;}
    
}
