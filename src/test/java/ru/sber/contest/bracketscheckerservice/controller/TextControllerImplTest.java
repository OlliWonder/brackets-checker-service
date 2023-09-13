package ru.sber.contest.bracketscheckerservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.sber.contest.bracketscheckerservice.api.request.TextCheckRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TextControllerImplTest {

    @Autowired
    protected MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TextCheckRequest correctTextMock = new TextCheckRequest("Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями.");
    private final TextCheckRequest incorrectTextMock = new TextCheckRequest("Вчера я отправился в () поход в лес.");
    private final TextCheckRequest incorrectRequestTextMock = new TextCheckRequest("");
    
    @Test
    void checkBracketsForCorrectText_200() throws Exception {
        String response = mvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(correctTextMock))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(response).isEqualTo("{\"isCorrect\":true}");
    }
    
    @Test
    void checkBracketsForIncorrectText_200() throws Exception {
        String response = mvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(incorrectTextMock))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(response).isEqualTo("{\"isCorrect\":false}");
    }
    
    @Test
    void checkBracketsForBadRequestEmptyString_400() throws Exception {
            mvc.perform(post("/api/checkBrackets")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(asJsonString(incorrectRequestTextMock))
                            .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is4xxClientError());
    }
    
    protected String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}