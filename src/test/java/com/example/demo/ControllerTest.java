package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MockMvcTester mockMvcTester;

  @MockitoBean
  private BookService bookService;

  @Test
  void test() throws Exception {
    when(bookService.getBooks()).thenReturn(List.of(
            new Book(1L, "Asterix le Gaulois", 1961),
            new Book(2L, "Clean Code", 2008))
    );

    mockMvc.perform(get("/books"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));
  }

  @Test
  void test2() {
    when(bookService.getBooks()).thenReturn(List.of(
            new Book(1L, "Asterix le Gaulois", 1961),
            new Book(2L, "Clean Code", 2008))
    );

    mockMvcTester.get()
            .uri("/books")
            .assertThat()
            .hasStatus(HttpStatus.OK)
            .bodyJson()
            .isEqualTo(
            //language=json
            """
            [
              {
                "id": 1,
                "title": "Asterix le Gaulois",
                "publicationYear": 1961
              },
              {
                "id": 2,
                "title": "Clean Code",
                "publicationYear": 2008
              }
            ]
            """
            );
  }
}
