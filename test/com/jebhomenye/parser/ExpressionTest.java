package com.jebhomenye.parser;

import static org.junit.Assert.*;
import static com.jebhomenye.parser.Token.TokenType.*;
import static com.jebhomenye.parser.Token.*;
import org.junit.Test;

public class ExpressionTest {

	@Test
	public void test() {
		Expression expression = new Expression("A + 100 - (B * C) / 2 ");
		
		Token expected = new Token("A", VARIABLE);
		assertEquals(expected, expression.getNextToken());
		
		assertEquals(PLUS, expression.getNextToken());
		assertEquals(new Token("100", NUMBER), expression.getNextToken());
		assertEquals(MINUS, expression.getNextToken());
		assertEquals(LEFT_BRAKET, expression.getNextToken());
		assertEquals(new Token("B", VARIABLE), expression.getNextToken());
		assertEquals(TIMES, expression.getNextToken());
		assertEquals(new Token("C", VARIABLE), expression.getNextToken());
		assertEquals(RIGHT_BRAKET, expression.getNextToken());
		assertEquals(DIVIDE, expression.getNextToken());
		assertEquals(new Token("2", NUMBER), expression.getNextToken());
		assertEquals(END_OF_EXPRESSION, expression.getNextToken());
	}

}
