package com.cardgame.card.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/game")
public class GameController {

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
