package com.cardgame.card.controllers;

import com.cardgame.card.domain.Player;

public interface IPlayerRepository {
	public void addPlayer(Player pPlayer);
	public void updatePlayer(Player pPlayer);
	public Player getPlayer(String pId);
	public void removePlayer(String pId);
	public String getNextPlayerId();
}
