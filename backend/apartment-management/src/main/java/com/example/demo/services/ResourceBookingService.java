package com.example.demo.services;

import com.example.demo.entities.ResourceBooking;
import java.util.List;
import java.util.Optional;

public interface ResourceBookingService {
    ResourceBooking createBooking(ResourceBooking booking);
    Optional<ResourceBooking> getBookingById(Long id);
    List<ResourceBooking> getAllBookings();
    String getStatus(Long bookingId);
    ResourceBooking updateBookingStatus(Long id, String status);
    void deleteBooking(Long bookingId);
    Optional<List<ResourceBooking>> getUserBookings(Long userId);
}
