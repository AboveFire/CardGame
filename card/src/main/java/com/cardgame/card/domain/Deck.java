package com.cardgame.card.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
	private String id = "";
	private List<Card> cards;
	
	
	public Deck() {
		cards = new ArrayList<Card>();
	}
	
	public Deck(String pId) {
		cards = new ArrayList<Card>();
		id = pId;
	}
	
	//Add one of each cards to a deck
	public void addAllCards() {
		for (Card.CardTypes type : Card.CardTypes.values()) {
			for (int i = Card.MIN_NUMBER; i <= Card.MAX_NUMBER; i++) {
				add(new Card(type, i));
			}
		}
	}
	
	public String getId() {
		return id;
	}

	//shuffle a deck in a random order
	public void shuffle() {
		//Algorithm Fisher-Yates https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
		for (int i = cards.size() - 1; i > 0; i--) {
			int swap = ThreadLocalRandom.current().nextInt(0, i + 1);
			Card cTemp = cards.get(i);
			cards.set(i, cards.get(swap));
			cards.set(swap, cTemp);
		}
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
	
	public boolean isEmpty() {
		return cards.size() == 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o instanceof Deck) {
			Deck d = (Deck) o;
			return this.getId().equals(d.getId());
		}
		return false;
	}
	
}
