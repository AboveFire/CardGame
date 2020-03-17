package com.cardgame.card.domain;

public class Card {
	public enum CardTypes {
		  HEART,
		  SPADE,
		  CLUB,
		  DIAMOND
		}
	public final static int MIN_NUMBER = 1;
	public final static int MAX_NUMBER = 13;

	private CardTypes type = null;
	private int number = 0;
	
	public Card(CardTypes type, int number) {
		setType(type);
		setNumber(number);
	}
	
	public CardTypes getType() {
		return type;
	}

	private void setType(CardTypes type) {
		if(type != null) {
			this.type = type;
		}else {
			throw new IllegalArgumentException();
		}
	}

	public int getNumber() {
		return number;
	}

	private void setNumber(int number) {
		if(number >= MIN_NUMBER && number <= MAX_NUMBER ) {
			this.number = number;
		}else {
			throw new IllegalArgumentException();
		}
	}
}
