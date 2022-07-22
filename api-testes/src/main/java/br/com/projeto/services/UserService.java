package br.com.projeto.services;

import java.util.List;

import br.com.projeto.domain.User;
import br.com.projeto.domain.dto.UserDTO;

public interface UserService {

	User findById(Integer id);
	List<User> findAll();
	User create(UserDTO dto);
	User update(UserDTO dto);
	void delete(Integer id);
	
}
