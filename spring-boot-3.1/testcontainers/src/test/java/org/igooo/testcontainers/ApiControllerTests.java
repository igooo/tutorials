package org.igooo.testcontainers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
class ApiControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	@BeforeEach
	void setUp() {
		this.userRepository.deleteAll();
	}

	@Test
	void shouldGetAllUsers() {
		this.userRepository.save(new User("igooo", 10));
		this.userRepository.save(new User("test", 20));
		this.userRepository.save(new User("three", 20));

		var users = this.restTemplate.getForObject("/api/users", User[].class);

		assertThat(users).hasSize(3);
		assertThat(users[0].getName()).isEqualTo("igooo");
		assertThat(users[1].getName()).isEqualTo("test");
		assertThat(users[2].getName()).isEqualTo("three");
	}

	@Test
	void shouldGetAllBooks() {
		this.bookRepository.save(new Book("igooo"));

		var users = this.restTemplate.getForObject("/api/books", User[].class);

		assertThat(users).hasSize(1);
		assertThat(users[0].getName()).isEqualTo("igooo");
	}

}