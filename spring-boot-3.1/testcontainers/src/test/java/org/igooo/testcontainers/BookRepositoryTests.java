package org.igooo.testcontainers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestcontainersConfiguration.class)
class BookRepositoryTests {

	@Autowired
	BookRepository bookRepository;

	@MockitoBean
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		bookRepository.deleteAllInBatch();
	}

	@Test
	void shouldGetAllBooks() {
		this.bookRepository.save(new Book("ring"));

		var books = this.bookRepository.findAll();

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getName()).isEqualTo("ring");
	}

}