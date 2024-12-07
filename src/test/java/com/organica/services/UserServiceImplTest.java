package com.organica.services;

import com.organica.config.JwtService;
import com.organica.entities.Cart;
import com.organica.entities.Role;
import com.organica.entities.TotalRoles;
import com.organica.entities.User;
import com.organica.payload.SingIn;
import com.organica.payload.UserDto;
import com.organica.repositories.UserRepo;
import com.organica.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserDto userDto;
    private User user;
    private SingIn singIn;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");

        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        singIn = new SingIn();
        singIn.setEmail("test@example.com");
        singIn.setPassword("password");
    }

    @Test
    void testCreateUser() {
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.CreateUser(userDto);

        assertNotNull(result);
        assertEquals(userDto.getEmail(), result.getEmail());
        verify(modelMapper, times(1)).map(userDto, User.class);
        verify(passwordEncoder, times(1)).encode(userDto.getPassword());
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void testSignIn() {
        when(userRepo.findByEmail(singIn.getEmail())).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn("mockJwtToken");

        SingIn result = userService.SingIn(singIn);

        assertNotNull(result);
        assertEquals("mockJwtToken", result.getJwt());
        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).generateToken(user);
        verify(userRepo, times(1)).findByEmail(singIn.getEmail());
    }







}
