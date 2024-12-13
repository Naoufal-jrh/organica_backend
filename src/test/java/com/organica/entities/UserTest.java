package com.organica.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        Cart cart = mock(Cart.class);

        user = new User(
                1,
                "John Doe",
                "johndoe@example.com",
                "password123",
                "123456789",
                new Date(),
                Arrays.asList(role1, role2),
                cart
        );
    }

    @Test
    void getAuthorities_shouldReturnCorrectAuthorities() {
        List<String> expectedAuthorities = Arrays.asList("ROLE_ADMIN", "ROLE_USER");

        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        assertEquals(expectedAuthorities, authorities);
    }

    @Test
    void getPassword_shouldReturnCorrectPassword() {
        assertEquals("password123", user.getPassword());
    }

    @Test
    void getUsername_shouldReturnCorrectUsername() {
        assertEquals("johndoe@example.com", user.getUsername());
    }

    @Test
    void isAccountNonExpired_shouldReturnTrue() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked_shouldReturnTrue() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired_shouldReturnTrue() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void isEnabled_shouldReturnTrue() {
        assertTrue(user.isEnabled());
    }

    @Test
    void getAndSetProperties_shouldWorkCorrectly() {
        user.setUserid(2);
        user.setName("Jane Doe");
        user.setEmail("janedoe@example.com");
        user.setPassword("newpassword");
        user.setContact("987654321");
        Date newDate = new Date();
        user.setDate(newDate);
        List<Role> newRoles = Arrays.asList(new Role("MANAGER"));
        user.setRole(newRoles);
        Cart newCart = mock(Cart.class);
        user.setCart(newCart);

        assertEquals(2, user.getUserid());
        assertEquals("Jane Doe", user.getName());
        assertEquals("janedoe@example.com", user.getUsername());
        assertEquals("newpassword", user.getPassword());
        assertEquals("987654321", user.getContact());
        assertEquals(newDate, user.getDate());
        assertEquals(newRoles, user.getRole());
        assertEquals(newCart, user.getCart());
    }
}
