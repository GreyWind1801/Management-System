package com.example.demo.services.impl;

import com.example.demo.entities.ResourceBooking;
import com.example.demo.repositories.ResourceBookingRepository;
import com.example.demo.services.ResourceBookingService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceBookingServiceImpl implements ResourceBookingService {

    private final ResourceBookingRepository bookingRepository;

    public ResourceBookingServiceImpl(ResourceBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public ResourceBooking createBooking(ResourceBooking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<ResourceBooking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<ResourceBooking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    @Override
    public String getStatus(Long bookingId) {
        return bookingRepository.findById(bookingId)
            .map(ResourceBooking::getStatus)  // Retrieve the status field
            .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
    
    @Override
    public void deleteBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new RuntimeException("Booking with ID " + bookingId + " not found");
        }
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public ResourceBooking updateBookingStatus(Long id, String status) {
    	ResourceBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        booking.setStatus(status);  // Ensure ResourceBooking has a setStatus() method
        return bookingRepository.save(booking);  // Return the updated object
    }
}
