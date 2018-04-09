package com.lsouza.user.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.lsouza.user.consts.TestConstants;
import com.lsouza.user.model.User;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(MockitoJUnitRunner.class)
public class UserUtilsTest {
	
	private User user;
	
	@BeforeClass
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates(TestConstants.PACKAGE);
	}
	
	@Before
	public void setupMock() {
		user = Fixture.from(User.class).gimme(TestConstants.USER);
	}
	
	@Test
	public void generateKey() {
		User generated = UserUtils.generateKey(User.builder()
								  .id(user.getId())
								  .username(user.getUsername())
								  .build());
		
		assertEquals(user.getKey(), generated.getKey());
	}
	
	@Test
	public void decodeKey() {
		String[] decoded = UserUtils.decodeKey(user.getKey());
		
		assertEquals(user.getId(), decoded[0]);
		assertEquals(user.getUsername(), decoded[1]);
	}

}
