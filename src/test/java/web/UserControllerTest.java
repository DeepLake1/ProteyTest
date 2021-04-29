package web;

import com.example.restservice.ProteyTestApplication;
import com.example.restservice.model.Status;
import com.example.restservice.model.StatusType;
import com.example.restservice.model.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest(classes = ProteyTestApplication.class)
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {
    private final String restURL = "/rest/user";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUserWith200() throws Exception {
        this.mockMvc.perform(get(restURL + "/90000").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserWith404() throws Exception {
        this.mockMvc.perform(get(restURL + "/99999").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void saveUser() throws Exception {
        User testUser = new User("TestUser", "mail22@gmail.com", "9811508555", LocalDateTime.now(), new Status(StatusType.ONLINE, LocalDateTime.now()));
        String jsonTestUser = new ObjectMapper().writer().writeValueAsString(testUser);
        this.mockMvc.perform(post(restURL + "/save").contentType(MediaType.APPLICATION_JSON).content(jsonTestUser))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void statusGetAndChange() throws Exception {
        this.mockMvc.perform(post(restURL + "/90000").param("status", "ONLINE")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser() throws Exception {
        this.mockMvc.perform(delete(restURL + "/90000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}