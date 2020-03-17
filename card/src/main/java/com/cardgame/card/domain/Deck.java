package com.cardgame.card.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> cards;
	
	public Deck() {
		this(false);
	}
	
	public Deck(boolean empty) {
		cards = new ArrayList<Card>();
		if (!empty) {
			for (Card.CardTypes type : Card.CardTypes.values()) {
				for (int i = Card.MIN_NUMBER; i <= Card.MAX_NUMBER; i++) {
					add(new Card(type, i));
				}
			}
		}
	}
	
	public void shuffle() {
		//TODO NEED TO DO MY OWN
		Collections.shuffle(cards);
	}
	
	public Card draw() {
		if(cards.size() > 0) {
			return cards.remove(0);
		}else {
			return null;
		}
	}
	
	public void add(Card pCard) {
		cards.add(pCard);
	}
	
	public void add(Deck pDeck) {
		cards.addAll(pDeck.getCards());
	}
	
	public void add(List<Card> pCards) {
		cards.addAll(pCards);
	}
	
	public List<Card> getCards(){
		return cards;
	}
	
	public int countType(Card.CardTypes pType) {
		int count = 0;
		for (Card card : cards) {
			if(card.getType() == pType) {
				count++;
			}
		}
		return count;
	}
	
}
