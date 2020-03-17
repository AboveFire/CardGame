package com.cardgame.card.domain;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player>{
	private String id = "";
	private String name = "";
	private List<Card> cards;

	public Player(String pId, String pName) {
		id = pId;
		cards = new ArrayList<Card>();
		setName(pName);
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String pName) {
		this.name = pName;
	}
	
	public void addCard(Card pCard) {
		if(pCard != null) {
			cards.add(pCard);
		}
	}
	
	public void addCards(List<Card> pCards) {
		cards.addAll(pCards);
	}
	
	public int getTotalCards() {
		int count = 0;
		for(Card card : cards) {
			count += card.getNumber();
		}
		return count;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o instanceof Player) {
			Player p = (Player) o;
			return this.getId().equals(p.getId());
			}
		return false;
	}

	@Override
	public int compareTo(Player o) {
		return getTotalCards() - o.getTotalCards();
	}  
}
