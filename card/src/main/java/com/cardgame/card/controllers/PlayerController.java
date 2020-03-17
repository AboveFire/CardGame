package com.cardgame.card.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cardgame.card.domain.Deck;
import com.cardgame.card.domain.Player;

@RestController
@RequestMapping("/player")
public class PlayerController {
	private final IPlayerRepository playerRepository;
		
		@Autowired
		public PlayerController(IPlayerRepository pPlayerRepository) {
			playerRepository = pPlayerRepository;
		}
		
		@PostMapping("/create")
		public Map<String, String> createPlayer(@RequestParam String name){
			Player player = new Player(playerRepository.getNextPlayerId(), name);
			playerRepository.addPlayer(player);
			
			Map<String, String> response = new HashMap<>();
			response.put("message", "Player created with id : " + player.getId());
			return response;
		}
}
