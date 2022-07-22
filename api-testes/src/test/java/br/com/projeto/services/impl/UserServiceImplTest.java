package br.com.projeto.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
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
import br.com.projeto.services.exceptions.DataIntegrityViolationException;
import br.com.projeto.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class UserServiceImplTest {

	private static final String E_MAIL_JA_CADASTRADO_EM_NOSSO_SISTEMA = "E-mail já cadastrado em nosso sistema!";
	private static final int INDEX = 0;
	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!!!";
	private static final Integer ID = 1;
	private static final String NAME = "Marcklen";
	private static final String EMAIL = "marcklen@email.com";
	private static final String PASSWORD = "123";

	@InjectMocks
	private UserServiceImpl serviceImpl;

	@Mock
	private UserRepository repository;

	@Mock
	private ModelMapper mapper;

	// mocando as classes Users
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

	// testando o metodo e quando encontrar retornar uma instancia de obj
	// when find by ID then return an user instance
	@Test
	void testFindById() {
		when(repository.findById(anyInt())).thenReturn(optionalUser);
		User response = serviceImpl.findById(ID);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
	}

	// testando metodo find by id porem agora com o uso das exceptiosn para retornar
	// ou nao um objeto
	@Test
	void testFindByIdWithObjectNotFoundException() {
		when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

		try {
			serviceImpl.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
		}
	}

	// testando o metodo para achar todos numa lista
	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(user));

		List<User> response = serviceImpl.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(User.class, response.get(INDEX).getClass());

		assertEquals(ID, response.get(INDEX).getId());
		assertEquals(NAME, response.get(INDEX).getName());
		assertEquals(EMAIL, response.get(INDEX).getEmail());
		assertEquals(PASSWORD, response.get(INDEX).getPassword());
	}

	@Test
	void testCreate() {
		when(repository.save(any())).thenReturn(user);
		User response = serviceImpl.create(userDTO);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
	}

	// testando o metodo de criar usuario e verificando se nao ha violacao de dados
	@Test
	void testCreateWithDataIntegratyViolationException() {
		when(repository.findByEmail(anyString())).thenReturn(optionalUser);

		try {
			optionalUser.get().setId(2);
			serviceImpl.create(userDTO);
		} catch (Exception e) {
			assertEquals(DataIntegrityViolationException.class, e.getClass());
			assertEquals(E_MAIL_JA_CADASTRADO_EM_NOSSO_SISTEMA, e.getMessage());
		}
	}

	@Test
	void testUpdate() {
		when(repository.save(any())).thenReturn(user);
		User response = serviceImpl.update(userDTO);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
	}

	@Test
	void testUpdateWithDataIntegratyViolationException() {
		when(repository.findByEmail(anyString())).thenReturn(optionalUser);

		try {
			optionalUser.get().setId(2);
			serviceImpl.create(userDTO);
		} catch (Exception e) {
			assertEquals(DataIntegrityViolationException.class, e.getClass());
			assertEquals(E_MAIL_JA_CADASTRADO_EM_NOSSO_SISTEMA, e.getMessage());
		}
	}

	@Test
	void testDelete() {
		when(repository.findById(anyInt())).thenReturn(optionalUser);
		doNothing().when(repository).deleteById(anyInt());
		serviceImpl.delete(ID);
		verify(repository, times(1)).deleteById(anyInt());
	}

	@Test
	void deleteWithObjectNotFoundException() {
		when(repository.findById(anyInt())).thenThrow(
				new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
		try {
			serviceImpl.delete(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
		}
	}

	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
	}
}