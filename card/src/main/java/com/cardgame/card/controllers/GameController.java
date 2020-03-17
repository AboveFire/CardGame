package com.cardgame.card.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

private final IGameRepository gameRepository;
	
	@Autowired
	public GameController(IGameRepository repository) {
		this.gameRepository = repository;
	}
	
	@GetMapping("/create")
	public ResponseEntity<String> createGame(){
		//TODO createGame
		return null;
	}
	
	@GetMapping("/addplayer")
	public ResponseEntity<String> addPlayer(){
		//TODO addPlayer
		return null;
	}
	
	@GetMapping("/removeplayer")
	public ResponseEntity<String> removePlayer(){
		//TODO removePlayer
		return null;
	}
}
