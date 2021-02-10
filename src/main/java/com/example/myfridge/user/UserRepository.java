package com.example.myfridge.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByFirstnameStartsWithIgnoreCaseOrSurnameStartsWithIgnoreCase(String firstname, String surname);
}
