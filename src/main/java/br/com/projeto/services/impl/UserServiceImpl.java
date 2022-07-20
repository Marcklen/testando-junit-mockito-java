package br.com.projeto.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.domain.User;
import br.com.projeto.repository.UserRepository;
import br.com.projeto.services.UserService;
import br.com.projeto.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findById(Integer id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!!!")); 
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
 