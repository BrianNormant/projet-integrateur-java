package main;

import org.junit.Test;

import api.Users;

import static org.junit.Assert.*;

import io.vavr.control.Either;
import java.awt.GraphicsEnvironment;

public class Test1 {
	@Test public void listfont() {
String fonts[] = 
			GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		for (int i = 0; i < fonts.length; i++) {
			System.out.println(fonts[i]);
		}
		assertTrue(fonts.length > 0);
	}
	
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
	}
}
