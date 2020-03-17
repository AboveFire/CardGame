package com.cardgame.card.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.cardgame.card.controllers.IPlayerRepository;
import com.cardgame.card.domain.Player;

public class PlayerRepositoryInMemory implements IPlayerRepository{
	Map<String, Player> players;
	
	public PlayerRepositoryInMemory() {
		players = new HashMap<>();
	}

	@Override
	public void addPlayer(Player pPlayer) {
		updatePlayer(pPlayer);
	}

	@Override
	public void updatePlayer(Player pPlayer) {
		if(!players.containsKey(pPlayer.getId())) {
			players.put(pPlayer.getId(),pPlayer);
		}
	}
	
	@Override
	public Player getPlayer(String pId) {
		return players.get(pId);
	}
	
	@Override
	public void removePlayer(String pId) {
		players.remove(pId);
	}

	@Override
	public String getNextPlayerId() {
		return UUID.randomUUID().toString();
	}
}
