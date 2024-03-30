package main;

import org.junit.Test;

import api.Users;

import static org.junit.Assert.*;

public class Test1 {
	@Test
	public void testRequestLogin() {
		var login = Users.requestLogin("admin", "1234");
		if (login.isRight()) {
			switch (login.get()) {
				case USER_NOT_FOUND -> System.out.println("User not found");
				case NOT_AUTHORIZED -> System.out.println("Not authorized");
			}
		}
		System.out.printf("token is %s", login.getLeft());
		assertTrue(login.isLeft());
	}
}
