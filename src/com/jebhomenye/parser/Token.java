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
	
	public static boolean isOperator(char chr){
		return " +-/*%^=()".indexOf(chr) != -1;
	}
	
	public static Token getOperator(char op){
		switch(op){
			case '+' : return PLUS;
			case '-' : return MINUS;
			case '*' : return TIMES;
			case '/' : return DIVIDE;
			case '%' : return MODULUS;
			case '^' : return EXPONENT;
			case '=' : return EQUALS;
			case '(' : return LEFT_BRAKET;
			case ')' : return RIGHT_BRAKET;
			default: throw new RuntimeException("Invalid Argument: " + op);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tokenType == null) ? 0 : tokenType.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (tokenType != other.tokenType)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}	
	
	public String toString(){
		return String.format("{%s, %s}", value, tokenType);
	}
	
	
}
