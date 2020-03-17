package com.cardgame.card.controllers;

import com.cardgame.card.domain.Deck;

public interface IDeckRepository {
	public void addDeck(Deck pDeck);
	public void updateDeck(Deck pDeck);
	public Deck getDeck(String pId);
	public void removeDeck(String pId);
	public String getNextDeckId();
}
