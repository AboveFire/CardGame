package com.cardgame.card.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.cardgame.card.domain.Deck;
import com.cardgame.card.domain.Game;
import com.cardgame.card.domain.Player;

@RestController
@RequestMapping("/game")
public class GameController {

private final IGameRepository gameRepository;
private final IDeckRepository deckRepository;
private final IPlayerRepository playerRepository;
	
	@Autowired
	public GameController(IGameRepository pGamepository, IDeckRepository pDeckRepository, IPlayerRepository pPlayerRepository) {
		gameRepository = pGamepository;
		deckRepository = pDeckRepository;
		playerRepository = pPlayerRepository;
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> createGame(){
		Deck deck = new Deck();
		deck.addAllCards();
		Game game = new Game(deck, gameRepository.getNextGameId());
		gameRepository.addGame(game);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Game created with id : " + game.getId());
		return response;
	}
	
	@PostMapping("/adddeck")
	public Map<String, String> addDeck(@RequestParam String gameId, @RequestParam String deckId) throws GameDoesNotExistException, DeckDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + "does not exist");
		Deck deck = deckRepository.getDeck(deckId);
		if(deck == null)
			throw new DeckDoesNotExistException("Deck " + deckId + "does not exist");
		game.addDeck(deck);
		gameRepository.updateGame(game);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Deck " + deckId + " added to game " + gameId);
		return response;
	}
	
	@PostMapping("/addplayer")
	public Map<String, String> addPlayer(@RequestParam String gameId, @RequestParam String playerId) throws PlayerDoesNotExistException, GameDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + "does not exist");
		Player player = playerRepository.getPlayer(playerId);
		if(player == null)
			throw new PlayerDoesNotExistException("Player " + playerId + "does not exist");
		game.addPlayer(player);
		gameRepository.updateGame(game);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Player " + playerId + " added to game " + gameId);
		return response;
	}
	
	@DeleteMapping("/removeplayer")
	public Map<String, String> removePlayer(@RequestParam String gameId, @RequestParam String playerId) throws GameDoesNotExistException, PlayerDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + "does not exist");
		Player player = playerRepository.getPlayer(playerId);
		if(player == null)
			throw new PlayerDoesNotExistException("Player " + playerId + "does not exist");
		game.removePlayer(player);
		gameRepository.updateGame(game);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Player " + playerId + " removed from game " + gameId);
		return response;
	}
	
	@ExceptionHandler({ GameDoesNotExistException.class, DeckDoesNotExistException.class, PlayerDoesNotExistException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleDoesNotExistException(Exception e) {
		Map<String, String> response = new HashMap<>();
		response.put("error", e.getMessage());
		return response;
    }
}
