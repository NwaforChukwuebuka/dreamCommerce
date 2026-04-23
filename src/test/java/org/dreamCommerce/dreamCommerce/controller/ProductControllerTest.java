package org.dreamCommerce.dreamCommerce.controller;

import tools.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dreamCommerce.dreamCommerce.dto.requests.AddProductRequest;
import org.dreamCommerce.dreamCommerce.dto.requests.UpdateProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName(
            """
                    Given:
                    When:
                    Check:
                    
                    """
    )
    @WithMockUser(roles="TEST")
    void testAddProduct() throws Exception {
        AddProductRequest productRequest = new AddProductRequest();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(productRequest)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    @WithMockUser(roles="TEST")
    void testUpdateProduct() {
        try {
            String productId = "550e8400-e29b-41d4-a716-446655440000";
            mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.PATCH, "/api/v1/products/" + productId)
                            .part(new MockPart("name", "candy".getBytes(StandardCharsets.UTF_8)))
                            .file(new MockMultipartFile("images", "image.jpg", "image/jpeg", "image content".getBytes()))
                            .with(SecurityMockMvcRequestPostProcessors.csrf()))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
        } catch (Exception e) {
            log.error("Error updating product: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Test
    void testThatProductImagesAreUploadededOnUpdate(){
        try {
             String productId = "550e8400-e29b-41d4-a716-446655440000";
             mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.PATCH, "/api/v1/products/" + productId)
                             .part(new MockPart("name", "candy".getBytes(StandardCharsets.UTF_8)))
                             .file(new MockMultipartFile("images", "image.jpg", "image/jpeg", "image content".getBytes()))
                             .with(SecurityMockMvcRequestPostProcessors.csrf()))
                     .andExpect(MockMvcResultMatchers.status().isOk())
                     .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
         } catch (Exception e) {
             log.error("Error updating product: {}", e.getMessage(), e);
             throw new RuntimeException(e);
         }

    }
}
