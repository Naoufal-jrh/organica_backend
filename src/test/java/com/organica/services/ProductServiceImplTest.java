package com.organica.services;

import com.organica.entities.Product;
import com.organica.payload.ProductDto;
import com.organica.repositories.ProductRepo;
import com.organica.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepo productRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1);
        product.setProductName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(10.0f);
        product.setWeight(1.0f);
        product.setImg(new byte[]{1, 2, 3});

        productDto = new ProductDto();
        productDto.setProductid(1);
        productDto.setProductName("Test Product");
        productDto.setDescription("Test Description");
        productDto.setPrice(10.0f);
        productDto.setWeight(1.0f);
        productDto.setImg(new byte[]{1, 2, 3});
    }

    @Test
    void testCreateProduct() {
        when(modelMapper.map(productDto, Product.class)).thenReturn(product);
        when(productRepo.save(any(Product.class))).thenReturn(product);
        when(modelMapper.map(any(Product.class), eq(ProductDto.class))).thenReturn(productDto);

        ProductDto result = productService.CreateProduct(productDto);

        assertNotNull(result);
        assertEquals("Test Product", result.getProductName());
        verify(productRepo, times(1)).save(any(Product.class));
    }

    @Test
    void testReadProduct() {
        when(productRepo.findById(1)).thenReturn(Optional.of(product));
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);

        ProductDto result = productService.ReadProduct(1);

        assertNotNull(result);
        assertEquals("Test Product", result.getProductName());
        verify(productRepo, times(1)).findById(1);
    }

    @Test
    void testReadAllProduct() {
        List<Product> products = new ArrayList<>();
        products.add(product);

        when(productRepo.findAll()).thenReturn(products);

        List<ProductDto> result = productService.ReadAllProduct();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getProductName());
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepo).deleteById(1);

        productService.DeleteProduct(1);

        verify(productRepo, times(1)).deleteById(1);
    }

    @Test
    void testUpdateProduct() {
        when(productRepo.findById(1)).thenReturn(Optional.of(product));
        when(productRepo.save(any(Product.class))).thenReturn(product);
        when(modelMapper.map(any(Product.class), eq(ProductDto.class))).thenReturn(productDto);

        ProductDto result = productService.UpdateProduct(productDto, 1);

        assertNotNull(result);
        assertEquals("Test Product", result.getProductName());
        verify(productRepo, times(1)).findById(1);
        verify(productRepo, times(1)).save(any(Product.class));
    }



}
