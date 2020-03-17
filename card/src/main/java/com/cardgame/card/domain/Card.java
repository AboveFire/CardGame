package com.cardgame.card.domain;

public class Card {
	public enum CardTypes {
		HEART("♥"),
		SPADE("♠"),
		CLUB("♣"),
		DIAMOND("♦");
		private final String symbol;
		CardTypes(String pSymbol){
			symbol = pSymbol;
		}
		public String getSymbol() {
			return symbol;
		}
	}
	public final static int MIN_NUMBER = 1;
	public final static int MAX_NUMBER = 13;

	private CardTypes type = null;
	private int number = 0;
	
	public Card(CardTypes pType, int pNumber) {
		setType(pType);
		setNumber(pNumber);
	}
	
	public CardTypes getType() {
		return type;
	}

	private void setType(CardTypes pType) {
		if(pType != null) {
			this.type = pType;
		}else {
			throw new IllegalArgumentException();
		}
	}

	public int getNumber() {
		return number;
	}

	private void setNumber(int pNumber) {
		if(pNumber >= MIN_NUMBER && pNumber <= MAX_NUMBER ) {
			this.number = pNumber;
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public String toString() {
		return type.getSymbol() + number;
	}
}
