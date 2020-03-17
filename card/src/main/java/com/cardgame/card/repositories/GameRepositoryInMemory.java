package com.cardgame.card.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.cardgame.card.controllers.IGameRepository;
import com.cardgame.card.domain.Game;

public class GameRepositoryInMemory implements IGameRepository {
	Map<String, Game> games;
	
	public GameRepositoryInMemory() {
		games = new HashMap<>();
	}

	@Override
	public void addGame(Game pGame) {
		updateGame(pGame);
	}

	@Override
	public void updateGame(Game pGame) {
		if(!games.containsKey(pGame.getId())) {
			games.put(pGame.getId(),pGame);
		}
	}
	
	@Override
	public Game getGame(String pId) {
		return games.get(pId);
	}
	
	@Override
	public void removeGame(String pId) {
		games.remove(pId);
	}

	@Override
	public String getNextGameId() {
		return UUID.randomUUID().toString();
	}
}
