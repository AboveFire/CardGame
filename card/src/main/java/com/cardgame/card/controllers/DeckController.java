package com.cardgame.card.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> createDeck(){
		Deck deck = new Deck(deckRepository.getNextDeckId());
		deck.addAllCards();
		deckRepository.addDeck(deck);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Deck created with id : " + deck.getId());
		return response;
	}
	
	@PostMapping("/shuffle")
	public Map<String, String> shuffleDeck(@RequestParam String deckId) throws DeckDoesNotExistException{
		Deck deck = deckRepository.getDeck(deckId);
		if(deck == null)
			throw new DeckDoesNotExistException("Deck " + deckId + " does not exist");
		deck.shuffle();
		deckRepository.updateDeck(deck);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Deck shuffled with id : " + deck.getId());
		return response;
	}
	
	@ExceptionHandler({ DeckDoesNotExistException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleDoesNotExistException(Exception e) {
		Map<String, String> response = new HashMap<>();
		response.put("error", e.getMessage());
		return response;
    }
}
