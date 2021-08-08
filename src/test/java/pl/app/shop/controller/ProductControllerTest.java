package pl.app.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import pl.app.shop.domain.dao.Category;
import pl.app.shop.domain.dto.CategoryDto;
import pl.app.shop.domain.dto.ProductDto;
import pl.app.shop.repository.CategoryRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldSaveProduct() throws Exception {

        Category savedCategory = Category.builder().name("AGD").build();
//        Category savedCategory = Category.builder().name("AGD").build();

        categoryRepository.save(savedCategory);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .param("startDate", "2021-08-aa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(ProductDto.builder()

                        .name("PRALKA")
                        .categoryId(savedCategory.getId())
                        .build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("PRALKA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(savedCategory.getId()));
    }
}