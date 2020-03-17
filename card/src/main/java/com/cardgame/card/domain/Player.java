package com.cardgame.card.domain;

public class Player{
	private String id = "";
	private String name = "";

	public Player(String pId, String pName) {
		id = pId;
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
}
