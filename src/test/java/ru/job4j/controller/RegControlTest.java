package ru.job4j.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.Main;
import ru.job4j.model.User;
import ru.job4j.service.AuthorityService;
import ru.job4j.service.UserService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RegControlTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private AuthorityService authorityService;

    @Test
    @WithMockUser
    void regPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void whenLoginPageCalledWithErrorMessage() throws Exception {
        this.mockMvc.perform(get("/reg")
                        .param("error", "errorMessage"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void whenUserRegistered() throws Exception {
        User user = new User("User", "123456");
        mockMvc.perform(post("/reg")
                        .flashAttr("user", user))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(service).save(argument.capture());
        assertThat(argument.getValue().getUsername(), is("User"));
    }

    @Test
    @WithMockUser
    public void whenUserWithSameLoginAlreadyExists() throws Exception {
        User user = new User("User", "123456");
        service.save(new User("User", "654321"));
        mockMvc.perform(post("/reg")
                        .flashAttr("user", user))
                .andDo(print())
                .andExpect(status().is(302));
    }
}
