package pl.app.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pl.app.shop.domain.dao.User;
import pl.app.shop.domain.dto.UserDto;
import pl.app.shop.repository.UserRepository;

import java.io.InputStream;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest//odpalanie testów
@AutoConfigureMockMvc//wstrzykiwanie mockMVC i wywoływać requestt na endpointach w kontolerze
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
//po wykonaniu testu cofa wszystkie zmiany i nie robi commita -
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveUser() throws Exception {
        mockMvc.perform(post("/api/users")//sciezka to endPoint
                .contentType(MediaType.APPLICATION_JSON)//JSON podajemy
                .content(objectMapper.writeValueAsBytes(UserDto.builder()//wypełniamy requestBody
                        .email("JAN@WP.PL")
                        .firstname("JAN")
                        .lastname("KOWAL")
                        .password("XXXX")
                        .confirmPassword("XXXX")
                        .build())))
                .andExpect(status().isOk())//czy odpowiedz ma status 200
                .andExpect(jsonPath("$.id").exists())//istnieje id
                .andExpect(jsonPath("$.password").doesNotExist())//tego pola nie moze byc w odpowiedzi
                .andExpect(jsonPath("$.email").value("JAN@WP.PL"))
                .andExpect(jsonPath("$.firstname").value("JAN"))
                .andExpect(jsonPath("$.lastname").value("KOWAL"));
    }

    @Test
    @WithMockUser(username = "user@wp.pl")
    void shouldUpdateUser() throws Exception {

        User userDb = userRepository.save(User.builder().email("user@wp.pl").build());

        mockMvc.perform(put("/api/users/" + userDb.getId())//sciezka to endPoint oraz metoda http
                .contentType(MediaType.APPLICATION_JSON)//JSON podajemy
                .content(objectMapper.writeValueAsBytes(UserDto.builder()//wypełniamy requestBody
                        .email("JAN@WP.PL")
                        .firstname("JAN")
                        .lastname("KOWAL")
                        .password("XXXX")
                        .confirmPassword("XXXX")
                        .build())))
                .andExpect(status().isOk())//czy odpowiedz ma status 200
                .andExpect(jsonPath("$.id").value(userDb.getId()))//istnieje id
                .andExpect(jsonPath("$.password").doesNotExist())//tego pola nie moze byc w odpowiedzi
                .andExpect(jsonPath("$.email").value("JAN@WP.PL"))//dokładna wartość
                .andExpect(jsonPath("$.firstname").value("JAN"))
                .andExpect(jsonPath("$.lastname").value("KOWAL"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateUserByAdmin() throws Exception {

        User userDb = userRepository.save(User.builder().email("user@wp.pl").build());

        mockMvc.perform(put("/api/users/" + userDb.getId())//sciezka to endPoint oraz metoda http
                .contentType(MediaType.APPLICATION_JSON)//JSON podajemy
                .content(objectMapper.writeValueAsBytes(UserDto.builder()//wypełniamy requestBody
                        .email("JAN@WP.PL")
                        .firstname("JAN")
                        .lastname("KOWAL")
                        .password("XXXX")
                        .confirmPassword("XXXX")
                        .build())))
                .andExpect(status().isOk())//czy odpowiedz ma status 200
                .andExpect(jsonPath("$.id").value(userDb.getId()))//istnieje id
                .andExpect(jsonPath("$.password").doesNotExist())//tego pola nie moze byc w odpowiedzi
                .andExpect(jsonPath("$.email").value("JAN@WP.PL"))//dokładna wartość
                .andExpect(jsonPath("$.firstname").value("JAN"))
                .andExpect(jsonPath("$.lastname").value("KOWAL"));
    }

    @Test
    void shouldNotUpdateUserByUnloggedUser() throws Exception {
        mockMvc.perform(put("/api/users/5")//sciezka to endPoint oraz metoda http
                .contentType(MediaType.APPLICATION_JSON)//JSON podajemy
                .content(objectMapper.writeValueAsBytes(UserDto.builder()//wypełniamy requestBody
                        .email("JAN@WP.PL")
                        .firstname("JAN")
                        .lastname("KOWAL")
                        .password("XXXX")
                        .confirmPassword("XXXX")
                        .build())))
                .andExpect(status().isForbidden())//czy odpowiedz ma status 200
                .andExpect(jsonPath("$").doesNotExist());//tego pola nie moze byc w odpowiedzi
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldFindUsersPage() throws Exception {

        userRepository.save(User.builder().email("TEST1@WP@PL").build());
        userRepository.save(User.builder().email("TEST2@WP@PL").build());

        mockMvc.perform(get("/api/users")
                .queryParam("page", "0")
                .queryParam("size", "2"))

                .andExpect(status().isOk())//czy odpowiedz ma status 200
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].email").value("TEST1@WP@PL"))
                .andExpect(jsonPath("$.content[1].email").value("TEST2@WP@PL"));
    }

    @Test
    void shouldUploadFile() throws Exception {
        InputStream file = getClass().getClassLoader().getResourceAsStream("assets/file.png");//w folderze resources jest folder asserts w którym jest plik który jest pobierany

        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "file.png", MediaType.IMAGE_PNG_VALUE, file);//nazwa zmiennej z requestParam z kontrolera, nazwa pliku, media type, nazwa zmiennej

        mockMvc.perform(multipart("/api/users/avatar")
                .file(mockMultipartFile)
                .with(processor -> {
                    processor.setMethod(HttpMethod.PATCH.toString());
                    return processor;
                }))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

    }

    @Test
    void shouldReturnBadRequestWithWrongBody() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(UserDto.builder().id(10L).confirmPassword("cosNowego").build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()").value(4));

    }

//    testy do ProductController, dodać role w metodach, podobnie w CategoryController, dodać do gitHub, można dodać swagger
}