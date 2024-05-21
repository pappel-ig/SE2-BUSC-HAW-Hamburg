package de.haw_hamburg.mensamatch;

import de.haw_hamburg.mensamatch.adapter.api.user.model.UserLoginDto;
import de.haw_hamburg.mensamatch.domain.user.UserRepository;
import de.haw_hamburg.mensamatch.domain.user.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    UserRepository userRepository;

    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

    @Test
    void integrationTest() {
        Mockito.when(userRepository.saveNewUser(any())).thenReturn(true);

        restTemplate.postForEntity("http://localhost:" + port + "/user/register",
                UserLoginDto.builder().username("test123").password("test").build(), Void.class);

        Mockito.verify(userRepository).saveNewUser(userArgumentCaptor.capture());

        assertEquals("test", userArgumentCaptor.getValue().getUsername());
        assertEquals(List.of("USER"), userArgumentCaptor.getValue().getRoles());
    }
}
