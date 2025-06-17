package com.example.demo;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AssertJTest {

  @Test
  void test1() {
    var list = List.of("a", "b", "c");
    assertThat(list)
            .isNotEmpty()
            .containsExactlyInAnyOrder("a", "b", "c");
  }

  @Test
  void test2() {
    var list = List.of(1);
    assertThat(list)
            .singleElement()
            .isEqualTo(1);
  }

  @Test
  void test3() {
    var user = new User("alice", 25);

    assertThat(user)
            .extracting(User::age)
            .isEqualTo(25);
  }

  @Test
  void test4() {
    var users = List.of(new User("alice", 25), new User("bob", 30));

    // JUnit assertions
    assertEquals(2, users.size());
    assertEquals("alice", users.getFirst().name());
    assertEquals("bob", users.get(1).name());

    // AssertJ
    assertThat(users)
            .hasSize(2)
            .extracting(User::name)
            .containsExactlyInAnyOrder("alice", "bob");
  }

  @Test
  void test5() {
    Instant now = Instant.now();
    Instant start = now.minusSeconds(12);
    Instant end = now.plusSeconds(42);
    assertThat(now)
            .isAfter(start)
            .isBefore(end)
            .isBetween(start, end);
  }

  @Test
  void test6() {
    var service = new BookService(new BookRepository());

    assertThatThrownBy(() -> service.failingMethod())
            .isInstanceOf(RuntimeException.class);
    assertThatCode(() -> service.notFailingMethod())
            .doesNotThrowAnyException();
  }

  @Test
  void test7() {
    var author = new Author("John", new Book(10L, "Java book", 2025));

    // Instead of this
    assertThat(author.book()).isNotNull();
    assertThat(author.book().id()).isEqualTo(10);
    assertThat(author.book().title()).isEqualTo("Java book");
    assertThat(author.book().publicationYear()).isEqualTo(2025);

    // do this
    assertThat(author.book())
            .isNotNull()
            .satisfies(
                    it -> {
                      assertThat(it.id()).isEqualTo(10L);
                      assertThat(it.title()).isEqualTo("Java book");
                      assertThat(it.publicationYear()).isEqualTo(2025);
                    });
  }

  @Test
  void test8() {
    var e1 = new Entity(1, "label");
    var e2 = new Entity(1, "label");


    assertEquals(e1.getId(), e2.getId());
    assertEquals(e1.getLabel(), e2.getLabel());

    assertThat(e1).usingRecursiveComparison().isEqualTo(e2);
  }
}
