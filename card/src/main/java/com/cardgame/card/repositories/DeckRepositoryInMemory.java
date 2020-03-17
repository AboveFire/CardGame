package com.cardgame.card.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.cardgame.card.controllers.IDeckRepository;
import com.cardgame.card.domain.Deck;

public class DeckRepositoryInMemory implements IDeckRepository {
	Map<String, Deck> decks;
	
	public DeckRepositoryInMemory() {
		decks = new HashMap<>();
	}

	@Override
	public void addDeck(Deck pDeck) {
		updateDeck(pDeck);
	}

	@Override
	public void updateDeck(Deck pDeck) {
		if(!decks.containsKey(pDeck.getId())) {
			decks.put(pDeck.getId(),pDeck);
		}
	}
	
	@Override
	public Deck getDeck(String pId) {
		return decks.get(pId);
	}
	
	@Override
	public void removeDeck(String pId) {
		decks.remove(pId);
	}

	@Override
	public String getNextDeckId() {
		return UUID.randomUUID().toString();
	}
}
