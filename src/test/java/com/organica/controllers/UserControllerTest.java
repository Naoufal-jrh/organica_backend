package com.organica.controllers;

import com.organica.payload.SingIn;
import com.organica.payload.UserDto;
import com.organica.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDto userDto;
    private SingIn singIn;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setName("testUser");
        userDto.setEmail("test@domain.com");
        userDto.setPassword("password");

        singIn = new SingIn();
        singIn.setEmail("testIn@domain.com");
        singIn.setPassword("password");
    }

    @Test
    void testSignUp() {
        when(userService.CreateUser(any(UserDto.class))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.CreateUser(userDto);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("testUser", response.getBody().getName());
        assertEquals("test@domain.com", response.getBody().getEmail());
    }

    @Test
    void testSignIn() {
        when(userService.SingIn(any(SingIn.class))).thenReturn(singIn);

        ResponseEntity<SingIn> response = userController.CreateUser(singIn);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("testIn@domain.com", response.getBody().getEmail());
    }
}
