package br.com.projeto.services;

import java.util.List;

import br.com.projeto.domain.User;

public interface UserService {

	User findById(Integer id);
	List<User> findAll();
}
