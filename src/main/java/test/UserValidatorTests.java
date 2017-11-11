package test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import api.ApiUtil;
import datastructures.User;

public class UserValidatorTests {

	User u = new User();
	ApiUtil util = new ApiUtil();
	
	
	@Before
	public void setUp() {
		u.setUser_name("sfdgfdg");
		u.setUser_pwd("gsdfgfd");
	}
	
	@Test
	public void validateValidUser() {
		assertTrue(util.validateUser(u));
	}
	
	@Test
	public void validateNoUsername() {
		u.setUser_name(null);
		assertTrue(!util.validateUser(u));
	}
	
	@Test
	public void validateNoPassword() {
		u.setUser_pwd(null);
		assertTrue(!util.validateUser(u));
	}
	
	
}
