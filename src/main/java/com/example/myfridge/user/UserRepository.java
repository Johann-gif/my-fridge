package com.example.myfridge.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByFirstnameStartsWithIgnoreCaseOrSurnameStartsWithIgnoreCase(String firstname, String surname);
}
