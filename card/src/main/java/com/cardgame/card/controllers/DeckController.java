package com.cardgame.card.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cardgame.card.repositories.DeckRepositoryInMemory;

@RequestMapping("/deck")
public class DeckController {
	
	private final IDeckRepository deckRepository;
	
	@Autowired
	public DeckController(IDeckRepository repository) {
		this.deckRepository = repository;
	}
	
	@GetMapping("/create")
	public ResponseEntity<String> createDeck(){
		
		return null;
	}
	
	@GetMapping("/delete")
	public ResponseEntity<String> deleteDeck(){
		//TODO createGame
		return null;
	}
}
