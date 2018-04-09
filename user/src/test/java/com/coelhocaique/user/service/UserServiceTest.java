package com.lsouza.user.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.lsouza.user.consts.TestConstants;
import com.lsouza.user.dto.UserDTO;
import com.lsouza.user.exception.UserException;
import com.lsouza.user.model.User;
import com.lsouza.user.repository.UserRepository;
import com.lsouza.user.service.impl.UserServiceImpl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	private UserServiceImpl userService;
	
	private User user;
	
	@BeforeClass
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates(TestConstants.PACKAGE);
	}
	
	@Before
	public void setupMock() {
		userService = new UserServiceImpl();
		userService.setUserRepository(userRepository);
		user = Fixture.from(User.class).gimme(TestConstants.USER);
	}
	
	@Test
	public void create() {
		when(userRepository.save((User)any())).thenReturn(user);
		UserDTO userDTO = userService.create(Fixture.from(UserDTO.class)
								  					.gimme(TestConstants.USER_DTO));
		
		assertEquals(user.getId(), userDTO.getId());
		assertEquals(user.getUsername(), userDTO.getUsername());
		assertEquals(user.getKey(), userDTO.getKey());
	}
	
	@Test
	public void find() throws UserException {
		when(userRepository.findOne((String)any())).thenReturn(null);
		UserDTO userDTO = userService.find("id");
		
		assertEquals(new Integer(HttpStatus.NO_CONTENT.value()), userDTO.getCode());
		assertEquals("User not found.", userDTO.getReturnMessage());
	}

}
