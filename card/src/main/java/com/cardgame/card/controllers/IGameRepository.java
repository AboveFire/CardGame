package com.cardgame.card.controllers;

import com.cardgame.card.domain.Game;

public interface IGameRepository {
	public void addGame(Game pDeck);
	public void updateGame(Game pDeck);
	public Game getGame(String pId);
	public void removeGame(String pId);
	public String getNextGameId();
}
