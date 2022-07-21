package br.com.projeto.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.projeto.domain.User;
import br.com.projeto.domain.dto.UserDTO;
import br.com.projeto.repository.UserRepository;

@SpringBootTest
class UserServiceImplTest {

	private static final Integer ID 		= 1;
	private static final String NAME 		= "Marcklen";
	private static final String EMAIL 		= "marcklen@email.com";
	private static final String PASSWORD	= "123";
	
	@InjectMocks
	private UserServiceImpl serviceImpl;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private ModelMapper mapper;
	
	//mocando as classes Users
	private User user;
	private UserDTO userDTO;
	private Optional<User> optionalUser;
	
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	//testando o metodo e quando encontrar retornar uma instancia de obj
	//when find by ID then return an user instance
	@Test
	void testFindById() {
		when(repository.findById(anyInt())).thenReturn(optionalUser);
		User response = serviceImpl.findById(ID);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		
		//fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
	}
}
