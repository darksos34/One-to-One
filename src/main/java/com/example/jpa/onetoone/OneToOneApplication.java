/*
 * Copyright (c) Jordy Coder
 */

package com.example.jpa.onetoone;

import com.example.jpa.onetoone.model.Gender;
import com.example.jpa.onetoone.model.User;
import com.example.jpa.onetoone.model.UserProfile;
import com.example.jpa.onetoone.repository.UserProfileRepository;
import com.example.jpa.onetoone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;

import java.util.Calendar;

@SpringBootApplication
@AllArgsConstructor
public class OneToOneApplication implements CommandLineRunner {


    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    public static void main(String[] args) {
        SpringApplication.run(OneToOneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Clean up database tables
        userProfileRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        //=========================================

        // Create a User instance
        User user = new User("Rajeev", "Singh", "rajeev@callicoder.com",
                "MY_SUPER_SECRET_PASSWORD");

        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1992, 7, 21);

        // Create a UserProfile instance
        UserProfile userProfile = new UserProfile("+91-8197882053", Gender.MALE, dateOfBirth.getTime(),
                "747", "2nd Cross", "Golf View Road, Kodihalli", "Bangalore",
                "Karnataka", "India", "560008");


        // Set child reference(userProfile) in parent entity(user)
        user.setUserProfile(userProfile);

        // Set parent reference(user) in child entity(userProfile)
        userProfile.setUser(user);

        // Save Parent Reference (which will save the child as well)
        userRepository.save(user);

        //=========================================
    }
}
