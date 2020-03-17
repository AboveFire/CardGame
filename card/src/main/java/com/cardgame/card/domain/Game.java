package com.cardgame.card.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	private String id = "";
	private Deck gameDeck;
	private List<Player> players;
	
	public Game(Deck pDeck, String pId) {
		id = pId;
		gameDeck = pDeck;
		players = new ArrayList<Player>();
	}
	
	public String getId() {
		return id;
	}
	
	public void addDeck(Deck pDeck) {
		gameDeck.add(pDeck);
	}
	
	public void addPlayer(Player pPlayer) {
		if(pPlayer != null && !players.contains(pPlayer)) {
			players.add(pPlayer);
		}
	}
	
	public void removePlayer(Player pPlayer) {
		players.remove(pPlayer);
	}
	
	public List<Player> getPlayerRank() {
		List<Player> orderedPlayers = new ArrayList<>();
		Collections.copy(players, orderedPlayers);
		Collections.sort(orderedPlayers);
		
		return orderedPlayers;
	}
	
	public void deal(Player pPlayer) {
		if(!gameDeck.isEmpty()) {
			pPlayer.addCard(gameDeck.draw());
		}
	}
	
	public void deal(Player pPlayer, int number) {
		for (int i = 0; i < number; i++) {
			deal(pPlayer);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o instanceof Game) {
			Game g = (Game) o;
			return this.getId().equals(g.getId());
			}
		return false;
	}
	
}
