package com.example.TravelAgency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.TravelAgency.entities.User;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User>findByUsername(String username);

}
