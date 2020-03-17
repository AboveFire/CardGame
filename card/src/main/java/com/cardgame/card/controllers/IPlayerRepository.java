package com.cardgame.card.controllers;

import com.cardgame.card.domain.Player;

public interface IPlayerRepository {
	public void addPlayer(Player pDeck);
	public void updatePlayer(Player pDeck);
	public Player getPlayer(String pId);
	public void removePlayer(String pId);
	public String getNextGameId();
}
