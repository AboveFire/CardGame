package com.cardgame.card.controllers;

public class PlayerDoesNotExistException extends Exception{
	public PlayerDoesNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
