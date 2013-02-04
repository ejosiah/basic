package com.jebhomenye.parser;


import static com.jebhomenye.parser.Token.TokenType.*;
import static com.jebhomenye.parser.Token.*;

public class Expression {
	int index;
	final private String value;
	
	public Expression(String value){
		this.value = value;
	}
	
	public Token getNextToken(){
		if(isLastToken()){
			return END_OF_EXPRESSION;
		}
		if(!isLastToken() && isWhiteSpace()){
			nextChar();
			return getNextToken();
		}
		if(isOperator()){
			return getOperator(nextChar());
		}
		if(isDigit()){
			 return new Token(buildString(), NUMBER);
		}
		if(isLetter()){
			return new Token(buildString(), VARIABLE);
		}
		return Token.END_OF_EXPRESSION;
	}
	
	public Token peek(){
		int currentIndex = index;
		Token token = getNextToken();
		index = currentIndex;
		return token;
	}
	
	private Character nextChar(){
		return value.charAt(index++);
	}
	
	private String buildString() {
		if(isLastToken() || isOperator()){
			return "";
		}
		return nextChar() + buildString();
	}

	private boolean isDigit() {
		return Character.isDigit(value.charAt(index));
	}

	private boolean isLastToken(){
		return index >= value.length();
	}
	
	private boolean isWhiteSpace(){
		char val = value.charAt(index);
		return Character.isWhitespace(val);
	}
	
	private boolean isOperator(){
		return Token.isOperator(value.charAt(index));
	}
	
	private boolean isLetter(){
		return Character.isLetter(value.charAt(index));
	}
	
	public void reset(){
		index = 0;
	}
	
	public String toString(){
		int idx = isLastToken() ? value.length() - 1 : index;
		return String.format("{%s}, position[%s]=%s", value, index, value.charAt(index));
	}
}
