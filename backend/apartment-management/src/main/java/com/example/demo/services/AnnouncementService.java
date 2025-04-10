package com.example.demo.services;

import com.example.demo.entities.Announcement;
import java.util.List;

public interface AnnouncementService {
    Announcement createAnnouncement(Announcement announcement);
    List<Announcement> getAllAnnouncements();
    List<Announcement> getAnnouncementsByAuthor(Long authorId);
    Announcement getAnnouncementById(Long id);
    void deleteAnnouncement(Long id);
}
