package com.cardgame.card.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Game {
	private String id = "";
	private Deck gameDeck;
	private Map<Player, List<Card>> players;
	
	public Game(Deck pDeck, String pId) {
		id = pId;
		gameDeck = pDeck;
		players = new HashMap<Player, List<Card>>();
	}
	
	public String getId() {
		return id;
	}
	
	public void addDeck(Deck pDeck) {
		gameDeck.add(pDeck);
	}
	
	public void addPlayer(Player pPlayer) {
		if(pPlayer != null && !players.containsKey(pPlayer)) {
			players.put(pPlayer, new ArrayList<Card>());
		}
	}
	
	public void removePlayer(Player pPlayer) {
		players.remove(pPlayer);
	}
	
	public List<Player> getPlayerRank() {
		List<Player> orderedPlayers = new ArrayList<>();
		
		players.entrySet().stream().sorted((Entry<Player, List<Card>> o1, Entry<Player, List<Card>> o2) -> {
			int total1 = 0;
			int total2 = 0;
			for(Card c : o1.getValue()) {
				total1 += c.getNumber();
			}
			for(Card c : o2.getValue()) {
				total2 += c.getNumber();
			}
			return total1 - total2;
		}).forEach(a -> orderedPlayers.add(a.getKey()));
		return orderedPlayers;
	}
	
	public void deal(Player pPlayer) {
		if(!gameDeck.isEmpty()) {
			players.get(pPlayer).add(gameDeck.draw());
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
