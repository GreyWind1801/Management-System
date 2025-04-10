package com.example.demo.repositories;

import com.example.demo.entities.Announcement;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
	List<Announcement> findByAuthor_UserId(Long userId);

}
