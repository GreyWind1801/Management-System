package com.example.demo.services.impl;

import com.example.demo.services.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path uploadDir = Paths.get("uploads");

    public FileStorageServiceImpl() throws IOException {
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        // Clean the filename and generate a unique name
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;

        // Resolve full path and save
        Path targetLocation = uploadDir.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        // Return relative URL path to be stored in the database
        return "/uploads/" + uniqueFilename;
    }
}
