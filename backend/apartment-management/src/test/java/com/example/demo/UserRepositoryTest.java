package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Ensure it doesn't use H2
@TestPropertySource(locations = "classpath:application-test.properties")  // Use a test-specific config
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // Arrange: Create and save a user
        User user = new User();
        user.setName("John Doe");
        user.setEmail("test@example.com");
        user.setPassword("securepassword123");  // ✅ Ensure password is set
        user.setPhone("9876543210");
        user.setRole(Role.RESIDENT);
        User savedUser = userRepository.save(user);

        // Assert: Check if the user is saved correctly
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUserId()).isNotNull();  // ✅ Ensures `getId()` is available
    }

    @Test
    public void testFindUserByEmail() {
        // Arrange: Save a user first
        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("jane@example.com");
        user.setPassword("newpassword123");  // ✅ Ensure password is set
        user.setPhone("9876543214");
        user.setRole(Role.RESIDENT);
        userRepository.save(user);

        // Act: Retrieve user by email
        Optional<User> foundUserOptional = userRepository.findByEmail("jane@example.com");

        // Assert: Check if user is found
        assertThat(foundUserOptional).isPresent();  // ✅ Ensures Optional<User> is handled correctly

        User foundUser = foundUserOptional.get();  // ✅ Extract User safely
        assertThat(foundUser.getEmail()).isEqualTo("jane@example.com");
    }
}
