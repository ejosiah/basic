package com.jebhomenye.parser;

public class Token {
	static enum TokenType{
		UN_DEFINED, OPERATOR, VARIABLE, NUMBER
	}
	
	static final Token END_OF_EXPRESSION = new Token('\0', TokenType.OPERATOR);
	static final Token PLUS = new Token('+', TokenType.OPERATOR);
	static final Token MINUS = new Token('-', TokenType.OPERATOR);
	static final Token TIMES = new Token('*', TokenType.OPERATOR);
	static final Token DIVIDE = new Token('/', TokenType.OPERATOR);
	static final Token MODULUS = new Token('%', TokenType.OPERATOR);
	static final Token EQUALS = new Token('=', TokenType.OPERATOR);
	static final Token EXPONENT = new Token('^', TokenType.OPERATOR);
	static final Token LEFT_BRAKET = new Token('(', TokenType.OPERATOR);
	static final Token RIGHT_BRAKET = new Token(')', TokenType.OPERATOR);
	
	final String value;
	final TokenType tokenType;
	
	public Token(Character value, TokenType tokenType){
		this.value = value.toString();
		this.tokenType = tokenType;
	}
	
	public Token(String value, TokenType tokenType){
		this.value = value;
		this.tokenType = tokenType;
	}
	
	public boolean isOperator(char chr){
		return " +-/*%^=()".indexOf(chr) != -1;
	}	
	
}
