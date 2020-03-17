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
		if(pPlayer != null && !players.contains(pPlayer)) {
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
	
	public void deal(Player pPlayer) {
		if(!deck.isEmpty()) {
			pPlayer.addCard(deck.draw());
		}
	}
	
	public void deal(Player pPlayer, int number) {
		for (int i = 0; i < number; i++) {
			deal(pPlayer);
		}
	}
	
}
