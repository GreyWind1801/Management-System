package com.example.demo.services.impl;

import com.example.demo.entities.Announcement;
import com.example.demo.repositories.AnnouncementRepository;
import com.example.demo.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Override
    public Announcement createAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    @Override
    public List<Announcement> getAnnouncementsByAuthor(Long authorId) {
        return announcementRepository.findByAuthor_UserId(authorId);
    }

    @Override
    public Announcement getAnnouncementById(Long id) {
        return announcementRepository.findById(id).orElseThrow(() -> new RuntimeException("Announcement not found"));
    }

    @Override
    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }
}
