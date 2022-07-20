package br.com.projeto.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.domain.User;
import br.com.projeto.domain.dto.UserDTO;
import br.com.projeto.repository.UserRepository;
import br.com.projeto.services.UserService;
import br.com.projeto.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User findById(Integer id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!!!")); 
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User create(UserDTO dto) {
		return userRepository.save(mapper.map(dto, User.class));
	}
}
 