package com.cardgame.card.domain;

public class Player {
	private String name = "";

	public Player(String pName) {
		setName(pName);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
