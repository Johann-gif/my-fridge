package com.example.myfridge.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));

        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));

        userRepository.delete(user);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<User> updateUser (@PathVariable int id, @RequestParam(required = false) String pseudo, @RequestParam(required = false) String password, @RequestParam(required = false) String surname, @RequestParam(required = false) String firstname, @RequestParam(required = false) String email, @RequestParam(required = false) String number) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));

        if (pseudo != null) {
            user.setPseudo(pseudo);
        }
        if (password != null) {
            user.setPassword(password);
        }
        if (surname != null) {
            user.setSurname(surname);
        }
        if (firstname != null) {
            user.setFirstname(firstname);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (number != null) {
            user.setMob_number(number);
        }
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestParam String pseudo, @RequestParam String password, @RequestParam String surname, @RequestParam String firstname, @RequestParam String email,@RequestParam(required = false) String number)  throws ResourceNotFoundException {
        User user = new User();

        user.setPseudo(pseudo);
        user.setPassword(password);
        user.setSurname(surname);
        user.setFirstname(firstname);
        user.setEmail(email);
        if (number != null) {
            user.setMob_number(number);
        }
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<User>> searchUser(@PathVariable String name) {
        List<User> usersByName = userRepository.findByFirstnameStartsWithIgnoreCaseOrSurnameStartsWithIgnoreCase(name, name);

        return ResponseEntity.ok().body(usersByName);
    }
}
