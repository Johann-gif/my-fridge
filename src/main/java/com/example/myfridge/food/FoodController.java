package com.example.myfridge.food;

import com.example.myfridge.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/foods")
public class FoodController {

    private static final String FOOD_NOT_FOUND = "Food not found :: ";
    private static final String USER_NOT_FOUND = "User not found :: ";

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Food> getFoods() {
        return foodRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFood(@PathVariable int id) throws ResourceNotFoundException {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(FOOD_NOT_FOUND + id));

        return ResponseEntity.ok().body(food);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood (@PathVariable int id) throws ResourceNotFoundException {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(FOOD_NOT_FOUND + id));

        foodRepository.delete(food);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Food> updateFood (@PathVariable int id, @RequestParam(required = false) String codean, @RequestParam(required = false) String libelle, @RequestParam(required = false) Integer quantite, @RequestParam(required = false) Date peremption, @RequestParam(required = false) Boolean perime) throws ResourceNotFoundException {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(FOOD_NOT_FOUND + id));

        if (codean != null) {
            food.setCodean(codean);
        }
        if (libelle != null) {
            food.setLibelle(libelle);
        }
        if (quantite != null) {
            food.setQuantite(quantite);
        }
        if (peremption != null) {
            food.setPeremption(peremption);
        }
        if (perime != null) {
            food.setPerime(perime);
        }
        foodRepository.save(food);
        return ResponseEntity.ok().body(food);
    }

    @PostMapping("/add")
    public ResponseEntity<Food> addPost(@RequestParam String codean, @RequestParam String libelle, @RequestParam Integer quantite, @RequestParam Date peremption, @RequestParam(required = false, defaultValue = "false") Boolean perime, @RequestParam int userid)  throws ResourceNotFoundException {
        Food food = new Food();

        food.setCodean(codean);
        food.setLibelle(libelle);
        food.setQuantite(quantite);
        food.setPeremption(peremption);
        if (perime != null) {
            food.setPerime(perime);
        }
        food.setUser(userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND + userid)));
        foodRepository.save(food);
        return ResponseEntity.ok().body(food);
    }

    @GetMapping("/search/{libelle}")
    public ResponseEntity<List<Food>> searchFood(@PathVariable String libelle) {
        List<Food> foodsByLibelle = foodRepository.findByLibelleIsContainingIgnoreCase(libelle);

        return ResponseEntity.ok().body(foodsByLibelle);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Food>> searchFoodByUser(@PathVariable int id) {
        List<Food> foodsByUser = foodRepository.getFoodsByUser(id);

        return ResponseEntity.ok().body(foodsByUser);
    }
    @GetMapping("/codean/{codean}")
    public ResponseEntity<List<Food>> searchFoodByUser(@PathVariable String codean) {
        List<Food> foodsByUser = foodRepository.findByCodean(codean);

        return ResponseEntity.ok().body(foodsByUser);
    }
}
