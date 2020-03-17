package com.cardgame.card.domain;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	public Map<Player, Integer> getPlayerLeaderBoard() {
		Map<Player, Integer> orderedPlayers = new HashMap<Player, Integer>();
		
		players.keySet().stream().sorted((Player p1, Player p2) -> {
			int total1 = getPlayerValue(p1);
			int total2 = getPlayerValue(p2);
			return total1 - total2;
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
	
	public Map<Card.CardTypes, Integer> getCountPerSuit(){
		Map<Card.CardTypes, Integer> map = new HashMap<Card.CardTypes, Integer>();
		for(Card.CardTypes type : Card.CardTypes.values()) {
			map.put(type, 0);
		}
		for (Card c : gameDeck.getCards()) {
			map.put(c.getType(), map.get(c.getType()) + 1);
		}
		return map;
	}
	
	public Map<Card, Integer> getCountPerCard(){
		Map<Card, Integer> cardCounts = new HashMap<Card, Integer>();
		for (Card.CardTypes type : Card.CardTypes.values()) {
			for (int i = Card.MIN_NUMBER; i <= Card.MAX_NUMBER; i++) {
				cardCounts.put(new Card(type, i), 0);
			}
		}
		for(Card c : gameDeck.getCards()) {
			cardCounts.put(c, cardCounts.get(c));
		}
		return cardCounts;
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
