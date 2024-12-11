package com.organica.controllers;

import com.organica.payload.ApiResponse;
import com.organica.payload.ProductDto;
import com.organica.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllersTest {

    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductControllers productControllers;

    @Mock
    private MultipartFile file;

    @Mock
    private MultiValueMap<String, String> formData;

    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        productDto = new ProductDto();
        productDto.setProductName("Test Product");
        productDto.setDescription("Test Description");
        productDto.setWeight(1.5f);
        productDto.setPrice(10.0f);
    }

    @Test
    void testCreateProduct() throws IOException {
        when(formData.getFirst("productname")).thenReturn("Test Product");
        when(formData.getFirst("description")).thenReturn("Test Description");
        when(formData.getFirst("weight")).thenReturn("1.5");
        when(formData.getFirst("price")).thenReturn("10.0");
        when(file.getBytes()).thenReturn(new byte[0]);
        when(productService.CreateProduct(any(ProductDto.class))).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productControllers.CreateProduct(formData, file);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Product", response.getBody().getProductName());
    }

    @Test
    void testGetById() {
        when(productService.ReadProduct(1)).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productControllers.GetById(1);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Product", response.getBody().getProductName());
    }

    @Test
    void testGetAll() {
        List<ProductDto> productList = new ArrayList<>();
        productList.add(productDto);
        when(productService.ReadAllProduct()).thenReturn(productList);

        ResponseEntity<List<ProductDto>> response = productControllers.getAll();

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Product", response.getBody().get(0).getProductName());
    }

    @Test
    void testDeleteProduct() {
        ResponseEntity<ApiResponse> response = productControllers.Delete(1);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Product deleted", response.getBody().getMessage());
    }

    @Test
    void testUpdateProduct() throws IOException {
        when(formData.getFirst("productname")).thenReturn("Updated Product");
        when(formData.getFirst("description")).thenReturn("Updated Description");
        when(formData.getFirst("weight")).thenReturn("2.0");
        when(formData.getFirst("price")).thenReturn("20.0");
        when(file.getBytes()).thenReturn(new byte[0]);
        when(productService.UpdateProduct(any(ProductDto.class), eq(1))).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productControllers.UpdateProduct(formData, file, 1);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Product", response.getBody().getProductName());
    }

}
