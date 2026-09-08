package com.chatboard.messanger.user;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveAndFindOnUsername() {
        User user = new User("Pasha", "pasha@example.com", "asasasw");
        userRepository.save(user);
        assertNotNull(user.getId());
        assertTrue(userRepository.findByUsername("Pasha").isPresent());
        assertTrue(userRepository.existsByEmail("pasha@example.com"));
        assertNotNull(user.getCreatedAt());
    }

    @Test
    public void existenceCheck() {
        User user = new User("Sasha", "sasha@example.com", "asasasww");
        userRepository.save(user);
        assertNotNull(user.getId());
        assertTrue(userRepository.findByUsername("Sasha").isPresent());
        assertFalse(userRepository.existsByUsername("несуществующий"));
    }

}
