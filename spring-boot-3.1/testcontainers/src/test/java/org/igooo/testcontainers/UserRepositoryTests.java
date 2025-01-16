package org.igooo.testcontainers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestcontainersConfiguration.class)
class UserRepositoryTests {

	@Autowired
	UserRepository userRepository;

	@BeforeEach
	void setUp() {
		this.userRepository.deleteAll();
	}

	@Test
	void shouldGetAllUsers() {
		this.userRepository.save(new User("igooo", 10));

		var users = this.userRepository.findAllByOrderById();

		assertThat(users).hasSize(1);
		assertThat(users.get(0).getName()).isEqualTo("igooo");
	}

}