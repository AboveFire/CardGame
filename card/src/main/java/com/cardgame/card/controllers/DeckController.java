package com.cardgame.card.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardgame.card.domain.Deck;

@RestController
@RequestMapping("/deck")
public class DeckController {
	
	private final IDeckRepository deckRepository;
	
	@Autowired
	public DeckController(IDeckRepository repository) {
		this.deckRepository = repository;
	}
	
	@GetMapping("/create")
	public ResponseEntity<String> createDeck(){
		Deck deck = new Deck(deckRepository.getNextDeckId());
		deck.addAllCards();
		deckRepository.addDeck(deck);
		return new ResponseEntity<>("Created deck with id : " + deck.getId(), HttpStatus.OK);
	}
}
