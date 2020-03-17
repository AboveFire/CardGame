package com.cardgame.card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cardgame.card.controllers.IDeckRepository;
import com.cardgame.card.controllers.IGameRepository;
import com.cardgame.card.controllers.IPlayerRepository;
import com.cardgame.card.repositories.DeckRepositoryInMemory;
import com.cardgame.card.repositories.GameRepositoryInMemory;
import com.cardgame.card.repositories.PlayerRepositoryInMemory;

@SpringBootApplication
@ComponentScan(basePackages = "com.cardgame.card.controllers")
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}
	
	@Bean
	public IDeckRepository iDeckRepository() {
		return new DeckRepositoryInMemory();
	}
	
	@Bean
	public IGameRepository iGameRepository() {
		return new GameRepositoryInMemory();
	}
	
	@Bean
	public IPlayerRepository iPlayerRepository() {
		return new PlayerRepositoryInMemory();
	}

}
