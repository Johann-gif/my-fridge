package com.example.myfridge;

import com.example.myfridge.food.FoodController;
import com.example.myfridge.user.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MyFridgeApplicationTests {

    @Autowired
    private UserController userController;
    @Autowired
    private FoodController foodController;
    @Autowired
    private MockMvc mockMvc;
    // Test userController existe
    @Test
    void contextLoadsUser() {
        assertThat(userController).isNotNull();
    }
    // Test foodController existe
    @Test
    void contextLoadsFood() {
        assertThat(foodController).isNotNull();
    }
    // Test sur l'endpoint /users renvoie 200
    @Test
    void shouldReturnUsers() throws Exception {
        this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk());
    }
    // Test sur l'endpoint /foods renvoie 200
    @Test
    void shouldReturnFoods() throws Exception {
        this.mockMvc.perform(get("/foods")).andDo(print()).andExpect(status().isOk());
    }

}
