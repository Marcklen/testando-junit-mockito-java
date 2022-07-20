package br.com.projeto.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.projeto.domain.User;
import br.com.projeto.repository.UserRepository;

/**
 *configuração do nosso perfil de testes
 */

@Configuration 
@Profile("local")
public class LocalConfig {

	@Autowired
	private UserRepository repository;
	
	@Bean
	public void startDB() {
		User u1 = new User(null, "Marcklen", "Marcklen@Email.com", "123");
		User u2 = new User(null, "Guimaraes", "Guimara@Email2.com", "321");
		
		repository.saveAll(List.of(u1, u2));
	}
}
