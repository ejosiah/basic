package com.jebhomenye.parser;

import com.jebhomenye.parser.Token.TokenType;

public class Expression {
	private int index;
	final private String value;
	
	public Expression(String value){
		this.value = value;
	}
	
	public Token getNextToken(){
		if(isLastToken()){
			return Token.END_OF_EXPRESSION;
		}
		
		while(!isLastToken() && isWhiteSpace()){
			index++;
		}
		
		if(isLastToken()){
			return Token.END_OF_EXPRESSION;
		}else if(isOperator()){
			return Token.getOperator(value.charAt(index++));
		}else if(isDigit()){
			String num = "";
			while(!isOperator()){
				num += value.charAt(index++);
				if(isLastToken()) break;
			}
			return new Token(num, TokenType.NUMBER);
		}else if(isLetter()){
			String val = "";
			while(!isOperator()){
				val += value.charAt(index++);
				if(isLastToken()) break;
			}
			return new Token(val, TokenType.VARIABLE);
		}
		return Token.END_OF_EXPRESSION;
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
}
