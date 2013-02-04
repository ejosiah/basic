package com.jebhomenye.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testEvaluate() {
		double expected = 7;
		double actual = Parser.evaluate("2 + 5");
		
		assertEquals(expected, actual, 0);
		 
		expected = 2 + 3 * 4/2;
		actual = Parser.evaluate("2 + 3 * 4 / 2");
		assertEquals(expected, actual, 0);
		
		expected = 2 * 4 + (2 + 3);
		actual = Parser.evaluate("2 * 4 + (2 + 3)");
		assertEquals(expected, actual, 0);
		
		expected = Math.pow(5, 2);
		actual = Parser.evaluate("5^2");
		assertEquals(expected, actual, 0);
		
	}
	
	@Test
	public void testExceptions(){
		try{
			Parser.evaluate("");
			fail("Should not get here");
		}catch(ParserException e){
			assertEquals("No expression", e.getMessage());
		}
		
		try{
			Parser.evaluate("5 + ");
			fail("Should not get here");
		}catch(ParserException e){
			assertEquals("Syntax Error", e.getMessage());
		}
		
		try{
			Parser.evaluate("2 * 4 + (2 + 3");
			fail("Should not get here");
		}catch(ParserException e){
			assertEquals("Missing parenthesis", e.getMessage());
		}
		
		try{
			Parser.evaluate("2/0");
			fail("Should not get here");
		}catch(ParserException e){
			assertEquals("Attempt to divide by Zero", e.getMessage());
		}
	}

}
