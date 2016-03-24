package user.list.controllers;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import user.list.config.WebConfig;
import user.list.entity.UserEntity;
import user.list.repositories.UserRepository;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class SignControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;


    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).
                apply(springSecurity()).
                dispatchOptions(true).build();
    }

    @Test
    public void upSuccess() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/sign/up/")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        builder.param("login", "awesome-login");
        builder.param("password", "password");
        builder.param("name", "name");
        builder.param("birthday", "2016-01-21");
        builder.param("sex", "0");
        builder.param("country", "country");
        ResultActions resultActions = mockMvc.perform(builder);
        //resultActions.andDo(MockMvcResultHandlers.print());
        resultActions.andExpect(MockMvcResultMatchers.status().is(302));

        List<UserEntity> userList = userRepository.findAll();
        assertTrue(userList.size() == 1);

        resultActions = mockMvc.perform(builder);
        //resultActions.andDo(MockMvcResultHandlers.print());
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

        userList = userRepository.findAll();
        assertTrue(userList.size() == 1);
    }

}
