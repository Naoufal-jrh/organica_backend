package com.organica.controllers;

import com.organica.payload.ApiResponse;
import com.organica.services.CartService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import com.organica.payload.CartDto;
import com.organica.payload.CartHelp;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {
    @Mock
    private CartService cartService;
    @Mock
    private Principal principal;
    @InjectMocks
    private CartController cartController;
    private CartHelp cartHelp;
    private CartDto cartDto;

    @BeforeEach
    void Setup(){
        cartHelp = new CartHelp();
        cartHelp.setProductId(1);
        cartHelp.setQuantity(2);

        cartDto = new CartDto();
        cartDto.setId(123);

        when(principal.getName()).thenReturn("test@domain.com");
    }

    @Test
    void testAddProduct(){
        when(cartService.addProductToCart(any(CartHelp.class))).thenReturn(cartDto);

        ResponseEntity<CartDto> response = cartController.addProduct(cartHelp, principal);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(123, response.getBody().getId());
        verify(cartService, times(1)).addProductToCart(any(CartHelp.class));
    }

    @Test
    void testGetCart() {
        when(cartService.GetCart("test@domain.com")).thenReturn(cartDto);

        ResponseEntity<CartDto> response = cartController.GetCart(principal);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(123, response.getBody().getId());
        verify(cartService, times(1)).GetCart("test@domain.com");
    }

    @Test
    void testDeleteItem() {
        ResponseEntity<ApiResponse> response = cartController.DeleteItem(principal, 1);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("remove", response.getBody().getMessage());
        verify(cartService, times(1)).RemoveById(1, "test@domain.com");
    }
}
