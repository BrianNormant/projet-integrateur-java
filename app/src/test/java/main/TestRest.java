package main;

import org.junit.Test;

import api.RestApi;

import static org.junit.Assert.*;

public class TestRest {

	static String token;
	@Test
	public void testRequestLogin() {
		var login = RestApi.requestLogin("admin", "1234");
		if (login.isRight()) {
			switch (login.get()) {
				case USER_NOT_FOUND -> System.out.println("User not found");
				case NOT_AUTHORIZED -> System.out.println("Not authorized");
			}
		}
		System.out.printf("token is %s", login.getLeft());
		token = login.getLeft();
		assertTrue(login.isLeft());
	}

	@Test
	public void testRequestCheckLogin() {
		var checkLogin = RestApi.requestCheckLogin("admin", token);
		assertTrue (checkLogin);
	}
}
