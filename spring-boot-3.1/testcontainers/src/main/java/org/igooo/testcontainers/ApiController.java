package org.igooo.testcontainers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	private final UserRepository userRepository;

	private final BookRepository bookRepository;

	ApiController(UserRepository userRepository, BookRepository bookRepository) {
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
	}

	@GetMapping("/users")
	List<User> getUsers() {
		return this.userRepository.findAllByOrderById();
	}

	@GetMapping("/books")
	List<Book> getBooks() {
		return this.bookRepository.findAll();
	}

}
