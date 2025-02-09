package com.cardgame.card.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.cardgame.card.domain.Card;
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
		response.put("id", game.getId());
		return response;
	}
	
	@DeleteMapping("/delete")
	public Map<String, String> deleteGame(@RequestParam String gameId) throws GameDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + " does not exist");
		gameRepository.removeGame(gameId);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Game deleted with id : " + game.getId());
		return response;
	}
	
	@PostMapping("/adddeck")
	public Map<String, String> addDeck(@RequestParam String gameId, @RequestParam String deckId) throws GameDoesNotExistException, DeckDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + " does not exist");
		Deck deck = deckRepository.getDeck(deckId);
		if(deck == null)
			throw new DeckDoesNotExistException("Deck " + deckId + " does not exist");
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
			throw new GameDoesNotExistException("Game " + gameId + " does not exist");
		Player player = playerRepository.getPlayer(playerId);
		if(player == null)
			throw new PlayerDoesNotExistException("Player " + playerId + " does not exist");
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
			throw new GameDoesNotExistException("Game " + gameId + " does not exist");
		Player player = playerRepository.getPlayer(playerId);
		if(player == null)
			throw new PlayerDoesNotExistException("Player " + playerId + " does not exist");
		if(!game.playerIsInGame(player))
			throw new PlayerDoesNotExistException("Player " + playerId + " is not part of the game");
		game.removePlayer(player);
		gameRepository.updateGame(game);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Player " + playerId + " removed from game " + gameId);
		return response;
	}
	
	@PostMapping("/dealcards")
	public Map<String, String> dealCards(@RequestParam String gameId, @RequestParam String playerId, @RequestParam(defaultValue="1") String numberDealt) throws PlayerDoesNotExistException, GameDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + " does not exist");
		Player player = playerRepository.getPlayer(playerId);
		if(player == null)
			throw new PlayerDoesNotExistException("Player " + playerId + " does not exist");
		if(!game.playerIsInGame(player))
			throw new PlayerDoesNotExistException("Player " + playerId + " is not part of the game");
		int number = 1;
		try {
			number = Integer.parseInt(numberDealt);
		}catch(Exception e) {}
		int numberDrawn = game.deal(player, number);
		gameRepository.updateGame(game);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Player " + playerId + " in game " + gameId + " given " + numberDrawn + " card(s)");
		return response;
	}
	
	@PostMapping("/shuffle")
	public Map<String, String> shuffle(@RequestParam String gameId) throws GameDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + " does not exist");
		game.shuffle();
		gameRepository.updateGame(game);
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Game shuffled with id : " + game.getId());
		return response;
	}
	
	@GetMapping("/gethand")
	public Map<String, Object> getHand(@RequestParam String gameId, @RequestParam String playerId) throws PlayerDoesNotExistException, GameDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + " does not exist");
		Player player = playerRepository.getPlayer(playerId);
		if(player == null)
			throw new PlayerDoesNotExistException("Player " + playerId + " does not exist");
		if(!game.playerIsInGame(player))
			throw new PlayerDoesNotExistException("Player " + playerId + " is not part of the game");
		
		
		List<Map<String, Object>> list = new ArrayList<>();
		for(Card card : game.getHand(player)) {
			Map<String,Object> cardMap = new HashMap<String, Object>();
			cardMap.put("type", card.getType().toString());
			cardMap.put("value", card.getNumber());
			list.add(cardMap);
		}
		Map<String, Object> response = new HashMap<>();
		response.put("hand", list);
		return response;
	}
	
	@GetMapping("/getplayers")
	public Map<String, Object> getPlayers(@RequestParam String gameId) throws GameDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + " does not exist");
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		for(Entry<Player, Integer> p : game.getPlayerLeaderBoard().entrySet()) {
			Map<String,Object> playerMap = new HashMap<String, Object>();
			playerMap.put("id", p.getKey().getId());
			playerMap.put("name", p.getKey().getName());
			playerMap.put("value", p.getValue());
			list.add(playerMap);
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("players", list);
		return response;
	}
	
	@GetMapping("/getcountpersuit")
	public Map<String, Object> getCountPerSuit(@RequestParam String gameId) throws GameDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + " does not exist");
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		for(Entry<Card.CardTypes, Integer> e : game.getCountPerSuit().entrySet()) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("type", e.getKey().toString());
			map.put("count", e.getValue());
			list.add(map);
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("types", list);
		return response;
	}
	
	@GetMapping("/getcountpercard")
	public Map<String, Object> getCountPerCard(@RequestParam String gameId) throws GameDoesNotExistException{
		Game game = gameRepository.getGame(gameId);
		if(game == null)
			throw new GameDoesNotExistException("Game " + gameId + " does not exist");
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		for(Entry<Card, Integer> e : game.getCountPerCard().entrySet()) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("type", e.getKey().getType().toString());
			map.put("value", e.getKey().getNumber());
			map.put("count", e.getValue());
			list.add(map);
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("cards", list);
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
