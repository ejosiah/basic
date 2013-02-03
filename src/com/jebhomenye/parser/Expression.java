package com.jebhomenye.parser;

import com.jebhomenye.parser.Token.TokenType;

public class Expression {
	private int Index;
	final private String value;
	
	public Expression(String value){
		this.value = value;
	}
	
	public Token getNextToken(){
		if(isLastToken()){
			return Token.END_OF_EXPRESSION;
		}
		
		while(!isLastToken() && isWhiteSpace()){
			++Index;
			
			if(isLastToken()){
				return Token.END_OF_EXPRESSION;
			}
			
			if(isDelimiter()){
				return new Token(value.charAt(Index++), TokenType.OPERATOR);
			}else if(isLetter()){
				String letter = "";
				while(!isDelimiter()){
					letter += value.charAt(Index++);
					if(isLastToken()) break;
				}
				return new Token(letter, TokenType.VARIABLE);
			}else if (isDigit()){
				String number = "";
				while(!isDelimiter()){
					number += value.charAt(Index++);
					if(isLastToken()) break;
				}
				return new Token(number, TokenType.NUMBER);
			}
		}
		return Token.END_OF_EXPRESSION;
	}
	
	
	private boolean isDigit() {
		return Character.isDigit(value.charAt(Index));
	}

	private boolean isLastToken(){
		return Index == value.length();
	}
	
	private boolean isWhiteSpace(){
		return Character.isWhitespace(value.charAt(Index));
	}
	
	private boolean isDelimiter(){
		return false;
	}
	
	private boolean isLetter(){
		return Character.isLetter(value.charAt(Index));
	}
	
	public void reset(){
		Index = 0;
	}
}
