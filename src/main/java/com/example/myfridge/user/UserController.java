package com.example.myfridge.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.List;

@RestController
// Je définis l'endpoint générique de la classe sur /users [GET]
@RequestMapping(path = "/users")
public class UserController {

    private static final String USER_NOT_FOUND = "User not found :: ";

    @Autowired
    private UserRepository userRepository;

    // Je définis sur /users/ la liste de toutes les users [GET]
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Je définis sur /users/{{id}} la recherche d'un user par son id [GET]
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND + id));

        return ResponseEntity.ok().body(user);
    }
    // Je définis sur /users/{{id}} la suppression d'un user par son id [DEL]
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND + id));

        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // Je définis sur /users/{{id}} la modification d'un user par son id [PUT]
    @PutMapping(path="/{id}")
    public ResponseEntity<User> updateUser (@PathVariable int id, @RequestParam(required = false) String pseudo, @RequestParam(required = false) String password, @RequestParam(required = false) String surname, @RequestParam(required = false) String firstname, @RequestParam(required = false) String email, @RequestParam(required = false) String number) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND + id));

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
            user.setMobnumber(number);
        }
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }
    // Je définis sur /users/add la création d'un user [POST]
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestParam String pseudo, @RequestParam String password, @RequestParam String surname, @RequestParam String firstname, @RequestParam String email,@RequestParam(required = false) String number)  throws ResourceNotFoundException {
        User user = new User();

        user.setPseudo(pseudo);
        user.setPassword(password);
        user.setSurname(surname);
        user.setFirstname(firstname);
        user.setEmail(email);
        if (number != null) {
            user.setMobnumber(number);
        }
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }
    // Je définis sur /users/search/{{name}} la recherche d'un user par son nom ou prénom [GET]
    @GetMapping("/search/{name}")
    public ResponseEntity<List<User>> searchUser(@PathVariable String name) {
        List<User> usersByName = userRepository.findByFirstnameStartsWithIgnoreCaseOrSurnameStartsWithIgnoreCase(name, name);

        return ResponseEntity.ok().body(usersByName);
    }
}
