package com.cardgame.card.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	
	//get players in order of the sum of their cards
	public Map<Player, Integer> getPlayerLeaderBoard() {
		Map<Player, Integer> orderedPlayers = new LinkedHashMap<Player, Integer>();
		
		//This comparator sorts in reverse order
		players.keySet().stream().sorted((Player p1, Player p2) -> {
			int total1 = getPlayerValue(p1);
			int total2 = getPlayerValue(p2);
			return total2 - total1;
		}).forEach(a -> orderedPlayers.put(a, getPlayerValue(a)));
		
		return orderedPlayers;
	}
	
	public int getPlayerValue(Player pPlayer) {
		int count = 0;
		for(Card c : players.get(pPlayer)) {
			count += c.getNumber();
		}
		return count;
	}
	
	//get the number of each cards in each suit and return a map of it
	public Map<Card.CardTypes, Integer> getCountPerSuit(){
		Map<Card.CardTypes, Integer> map = new LinkedHashMap<Card.CardTypes, Integer>();
		for(Card.CardTypes type : Card.CardTypes.values()) {
			map.put(type, 0);
		}
		for (Card c : gameDeck.getCards()) {
			map.put(c.getType(), map.get(c.getType()) + 1);
		}
		return map;
	}
	
	//return an array containing all possible cards and their number in the gameDeck
	public Map<Card, Integer> getCountPerCard(){
		Map<Card, Integer> cardCounts = new LinkedHashMap<Card, Integer>();
		for (Card.CardTypes type : Card.CardTypes.values()) {
			for (int i = Card.MAX_NUMBER; i >= Card.MIN_NUMBER; i--) {
				cardCounts.put(new Card(type, i), 0);
			}
		}
		for(Card c : gameDeck.getCards()) {
			cardCounts.put(c, cardCounts.get(c) + 1);
		}
		return cardCounts;
	}
	
	public boolean deal(Player pPlayer) {
		if(!gameDeck.isEmpty()) {
			players.get(pPlayer).add(gameDeck.draw());
			return true;
		}
		return false;
	}
	
	//Give a specified number of cards to a player
	public int deal(Player pPlayer, int number) {
		int count = 0;
		for (int i = 0; i < number; i++) {
			if(deal(pPlayer)) {
				count++;
			}else {
				break;
			}
		}
		return count;
	}
	
	public boolean playerIsInGame(Player pPlayer) {
		return players.containsKey(pPlayer);
	}
	
	public void shuffle() {
		gameDeck.shuffle();
	}
	
	public List<Card> getHand(Player pPlayer){
		return players.get(pPlayer);
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
