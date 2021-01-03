package com.example.myfridge.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.myfridge.Food.Food;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pseudo;
    private String password;
    private String surname;
    private String firstname;
    private String email;
    private String mob_number;
}
