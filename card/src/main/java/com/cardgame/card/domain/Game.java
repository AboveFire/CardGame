package com.cardgame.card.domain;

import java.util.List;

public class Game {
	private Deck deck;
	private List<Player> players;
	
	public Game() {
		deck = new Deck(true);
	}
	
	
}
