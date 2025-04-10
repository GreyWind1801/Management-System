package com.example.demo.repositories;

import com.example.demo.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    // Custom query method to find all apartments by block
    List<Apartment> findByBlock(String block);
}
