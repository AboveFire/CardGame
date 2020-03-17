package com.cardgame.card.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardgame.card.domain.Deck;

@RestController
@RequestMapping("/deck")
public class DeckController {
	
	private final IDeckRepository deckRepository;
	
	@Autowired
	public DeckController(IDeckRepository pDeckRepository) {
		deckRepository = pDeckRepository;
	}
	
	@PostMapping("/create")
	public Map<String, String> createDeck(){
		Deck deck = new Deck(deckRepository.getNextDeckId());
		deck.addAllCards();
		deckRepository.addDeck(deck);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Deck created with id : " + deck.getId());
		return response;
	}
}
