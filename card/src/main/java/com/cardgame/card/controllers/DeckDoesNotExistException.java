package com.cardgame.card.controllers;

public class DeckDoesNotExistException extends Exception{
	public DeckDoesNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
