package kjstyle.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import kjstyle.base.BaseMockMvcTest;
import kjstyle.domain.repository.User;
import kjstyle.domain.repository.UserRepository;
import kjstyle.web.dto.UserCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends BaseMockMvcTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    private Long insertedUserNo;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .userId("kjstyle")
                .password("secret")
                .name("길주")
                .email("kj@hanatour.com")
                .nickName("길스타")
                .birthYearMonth("199004")
                .build();

        User insertedUser = userRepository.save(user);
        insertedUserNo = insertedUser.getUserNo();
    }

    @Test
    @DisplayName("GET /users - DB에 있는 유저 조회")
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("kjstyle"))
                .andExpect(jsonPath("$[0].email").value("kj@hanatour.com"));
    }

    @Test
    @DisplayName("GET /users/{id} - 단일 유저 조회")
    void getUserById() throws Exception {
        mockMvc.perform(get("/users/" + insertedUserNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("길주"));
    }

    @Test
    @DisplayName("POST /users - 유저 생성")
    void createUser() throws Exception {
        UserCreateDto dto = UserCreateDto.builder()
                .userId("tester")
                .password("pw1234")
                .passwordCheck("pw1234")
                .name("테스터")
                .email("tester@example.com")
                .nickName("testnick")
                .birthYearMonth("199101")
                .build();

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("tester"))
                .andExpect(jsonPath("$.email").value("tester@example.com"));
    }
}
