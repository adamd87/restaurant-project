package pl.adamd.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.adamd.restaurant.products.Product;
import pl.adamd.restaurant.products.ProductController;
import pl.adamd.restaurant.products.ProductRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductRepository productRepository;


    @Test
    @WithMockUser
    void getAllProductsTest() throws Exception {
        when(productRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Product(1L, "Salt", new BigDecimal("50.489"), new BigDecimal("150.99")),
                        new Product(2L, "Sugar", new BigDecimal("522.555"), new BigDecimal("250.98")),
                        new Product(3L, "Beef", new BigDecimal("52.150"), new BigDecimal("50.57"))));

        mockMvc.perform(get("/products/get-all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[1].name", is("Sugar")));

        when(productRepository.findByName("Salt"))
                .thenReturn(Collections.singletonList(
                        new Product(1L, "Salt", new BigDecimal("50.489"), new BigDecimal("150.99")))
                );
        mockMvc.perform(get("/products/by-name/salt"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void addNewProductTest() throws Exception {
        when(productRepository.save(any()))
                .thenReturn(new Product(4L, "Pork", new BigDecimal("15.25"), new BigDecimal("25.16")));

        //language=JSON
        String productJson = "{\n" +
                "  \"name\": \"Pork\",\n" +
                "  \"weight\": \"15.25\",\n" +
                "  \"cost\": \"25.16\"\n" +
                "}";

        MockHttpServletRequestBuilder requestBuilder = post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4));
    }

    @Test
    @WithMockUser
    void deleteTest() throws Exception {
        when(productRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Product(1L, "Salt", new BigDecimal("50.489"), new BigDecimal("150.99")),
                        new Product(2L, "Sugar", new BigDecimal("522.555"), new BigDecimal("250.98")),
                        new Product(3L, "Beef", new BigDecimal("52.150"), new BigDecimal("50.57"))));

        productRepository.deleteById(1L);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isGone());
    }

    @Test
    @WithMockUser
    void patchProductTest() throws Exception {
        when(productRepository.findOneByName("Sugar"))
                .thenReturn(
                        new Product(2L, "Sugar", BigDecimal.valueOf(522.55), BigDecimal.valueOf(250.98)));

        //language=JSON
        String productJson = "{\n" +
                "  \"name\": \"Sugar\",\n" +
                "  \"weight\": 0.45\n" +
                "}";
        MockHttpServletRequestBuilder requestBuilder = patch("/products/Sugar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.weight").value(BigDecimal.valueOf(523.00)));

    }
}
