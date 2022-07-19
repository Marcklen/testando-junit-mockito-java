package br.com.projeto.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		
		return ResponseEntity.ok().body(new User(1, "Marcklen", "Marcklen@email.com", "123"));
	}
}
