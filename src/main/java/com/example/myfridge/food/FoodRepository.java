package com.example.myfridge.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findByLibelleIsContainingIgnoreCase(String libelle);

    List<Food> findByCodean(String codean);
    @Query(value = "SELECT * FROM food WHERE userid = :id",
            nativeQuery = true)
    List<Food> getFoodsByUser(
            @Param("id") Integer id
    );
}