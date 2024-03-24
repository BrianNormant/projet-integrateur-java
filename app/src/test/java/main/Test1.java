package main;

import org.junit.Test;
import static org.junit.Assert.*;

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
	
}
