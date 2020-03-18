# CardGame
Basic Deck of Cards Game for LogMeIn

**POST:**
```
/player/create
  parameters : name (String)
  description : Create a player with defined name and returns the id in the json

/deck/create
  description : Create a standard deck with 52 cards and returns the id in the json
/deck/shuffle
  parameters : deckId (String)
  description : Shuffle the deck of specified id using Fisher–Yates shuffle algorithm

/game/create
  description : Create a game with a standard starting deck of 52 cards and returns the id in the json
/game/addplayer
  parameters :  gameId (String)
                playerId (String)
  description : Add a player with a specified id to a game with a specified id
/game/adddeck
  parameters :  gameId (String)
                deckId (String)
  description : Add a deck with a specified id to a game with a specified id
/game/dealcards
  parameters :  gameId (String)
                playerId (String)
                numberDealt (Number (default: 1))
  description : Give card(s) from the game deck of a specified game to a specified player in the game
/game/shuffle
  parameters : gameId (String)
  description : Shuffle the game deck of a game with a specified id using Fisher–Yates shuffle algorithm
```
**DELETE:**
```
/game/delete
  parameters : gameId (String)
  description : Delete the game with the specified id
/game/removeplayer
  parameters : gameId (String)
  parameters : playerId (String)
  description : Remove player with a specified id from a game with a specified id
```
**GET:**
```
/game/gethand
  parameters : gameId (String)
  parameters : playerId (String)
  description : Get the hand of a player with a specified id within a specified game
/game/getplayers
  parameters : gameId (String)
  description : Get all the players from a specific game with the value of their hand in decreasing order
/game/getcountpersuit
  parameters : gameId (String)
  description : Get the number of cards from each suit in a specific game
/game/getcountpercard
  parameters : gameId (String)
  description : Get the number of each possible cards that are currently in the game deck of a specified game
```
