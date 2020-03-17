package com.cardgame.card.controllers;

public class GameDoesNotExistException extends Exception{
	public GameDoesNotExistException(String errorMessage) {
		super(errorMessage);
	}
}
