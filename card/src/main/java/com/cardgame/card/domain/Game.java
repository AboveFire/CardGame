package com.cardgame.card.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	private Deck deck;
	private List<Player> players;
	
	public Game() {
		deck = new Deck(true);
		players = new ArrayList<Player>();
	}
	
	public void addDeck() {
		deck.add(new Deck());
	}
	
	public void addPlayer(Player pPlayer) {
		if(pPlayer != null) {
			players.add(pPlayer);
		}
	}
	
	public void removePlayer(Player pPlayer) {
		players.remove(pPlayer);
	}
	
	public void removePlayer(String pPlayerName) {
		removePlayer(new Player(pPlayerName));
	}
	
	public List<Player> getPlayerRank() {
		List<Player> orderedPlayers = new ArrayList<>();
		Collections.copy(players, orderedPlayers);
		Collections.sort(orderedPlayers);
		
		return orderedPlayers;
	}
	
}
