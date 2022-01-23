# PocketBeast
A simple Java Trading card game to demonstrate Design patterns.

## Game Rules
New players each start with 15 Health and 1 Mana to spend on playing cards.
At the start of the game each player draws 4 cards from their deck to hand.

Players each take turns. Each turn consists four phases:
1. Add mana (mana increases by one each turn and replenishes in full).
2. Draw a card.
3. There are 2 types of cards :
   - Beast Card : Cards with powers to attack another person's card.
   - Trainer Card : Cards which can increase powers of Beast card.
   - Note : You can't play Trainer card on its own, you can only play them on top of Beast card and only can be played once.
4. Cycle through your cards in play (if any), choosing whether to attack.
   - Attacking the other player directly with your card inflicts damage to their health equal to the attack power of the card.
   -  Attacking another players beast will damage both cards (equal to their attack values).
   -  Any card with <= 0 health is removed from the play field and placed into the graveyard.
5. Play cards from hand.
## Details of cards
- Beast Card : Type of card, which will help you to damage another player or their cards.
- Trainer Card : Type of card which helps the Beast card to survive also increases the powers.


![cardDetails](https://user-images.githubusercontent.com/73232849/150696489-fe1b4cdb-ce40-4292-95d0-5192a5c2843d.jpg)


## Game preview

### New Game
- Need to run ServerMain.java and then ClientMain.java.
- Then it asks for the name of the player and starts the game.


![EnterYourName](https://user-images.githubusercontent.com/73232849/150696699-34cb0216-bf3e-43fd-ada7-a10bb40e8c96.png)


### Game Started
You have "Hand" of a player which represents the card in the hand. Opponent can't see these cards.
Then you have "InPlay" these are the card you've played and are ready to attack. Opponent can see these cards.
At last you've got "Opponent's InPlay" which represents the cards opponent has played and are ready to attack. 


- Player 1 :


![GameStartP1](https://user-images.githubusercontent.com/73232849/150696781-472b3510-ffb8-4087-a910-83b78834759c.png)


- Player 2 :


![GameStartP2](https://user-images.githubusercontent.com/73232849/150696785-b65e68d9-9c93-4be3-87d5-927549778caa.png)


### Chat feature

Players can chat during the game.


![GameMidP1_2](https://user-images.githubusercontent.com/73232849/150696943-bbbbe98e-e172-4a0e-a866-0f9ba358b39b.png)



-There are some bugs in the game at the moment.



