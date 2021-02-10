package com.example.myfridge;

import com.example.myfridge.Food.FoodController;
import com.example.myfridge.User.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
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

    @Test
    void contextLoadsUser() {
        assertThat(userController).isNotNull();
    }

    @Test
    void contextLoadsFood() {
        assertThat(foodController).isNotNull();
    }

    @Test
    public void shouldReturnUsers() throws Exception {
        this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnFoods() throws Exception {
        this.mockMvc.perform(get("/foods")).andDo(print()).andExpect(status().isOk());
    }

}
