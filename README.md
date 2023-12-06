
# Brick Breaker Game 🧱

Brick Breaker Game is a classic arcade video game. The player's goal is to destroy all the bricks in the scree. The player needs to control a paddle to catch a small ball. Here are the simple command:

- ⬆️ ⬇️ Change Option

- ⬅️     Move left

- ➡️     Move right

- P      Pause Menu

- S      Save Game

- Enter  Select Option



## Version Control

Github is used for commit and push.

gitlink: https://github.com/stupidFLOWERch/COMP2042_CW_efyct3

## Implemented and Working Properly

## Additional Features

- ### Main Menu

        - 5 buttons are design on Main Menu, which are Load a Game, Start a New Game, Instruction ,High Score Page and Exit.
        - If there are not any saved game progress, Load A Game button will be hide.
        - The Main Menu is redesign by changing the background image, adding buttons and using new fonts.
        
- ### Instruction Page
        
        - Instruction Page is displayed when Instruction button is pressed. The page is added to show the command that player can do to the game.
        - Back to Main Menu button can be pressed to return to the Main Menu.

- ### High Score Page 

        - High Score Page is displayed when High Score Page button is pressed. The page is added to show the player's name and the highest score achieved in the game. 
        - Back to Main Menu button can be pressed to return to the Main Menu.


- ### Exit Game
        - When Exit Button is pressed, the game will exit.

- ### Pause Game
        - When keyboard button 'P' is pressed, the game will pause and freeze at the moment paused.
        

- ### Pause Menu
        - There are 3 buttons on Pause Menu, which are Resume, Exit and Restart Level.
        - When the game is paused, Pause Menu is displayed. Otherwise, it is hidden.
        
- ### Background Music
        - Background music is added to the game.
        - The background music will pause and resume with the game.

- ### Brick Destroyed Sound Effect
        - Crack sound is play when brick is destroyed.

- ### Resume Game
        - If 'P' or resume button in Pause Menu is pressed again when the game is paused, the game will resume at the point the player pause the game. 

- ### Restart Level
        - When Restart Level button in Pause Menu is pressed, the game will restart the current level by reset the heart and score to the beginning of the level.


- ### Win Page
        - When the players pass level 17, the screen will display win and ask player to play for bonus level or restart Game.
        - There are two button in the page, which are Bonus Level and Restart button.

- ### Bonus Level 
        - When player press on Bonus Level button, player will proceed to the last level which is Bonus Level.


- ### Pass Game Page
        - When player pass the Bonus Level, the game will congrats player and display a Exit button.

- ### Pass Game Sound Effect
        - When bonus level is passed, the Pass Game sound will played.

- ### Mousetrap block
        - After player pass level 5, mousetrap block will random generate.
        - If player hit the blocks, mousetrap will drop down from the blocks' postion.
        - If player get the mousetrap, it will decrease player's mark by 3.

- ### Save Highest Score and Player's name
        - If players achieve the highest score and loss the game or win the game, the game will display a dialog box for player to enter name.
        - After the name is entered and ok is press, the highest score will saved with players's name.

- ### Rewards if pass a level without lossing heart
        - If player pass a level without lossing any hearts, extra scores will be reward to the player.

## Refactoring

- ### Load Game 
        - When Load a Game button pressed, the game will load and start from the progress where the player saved for the previous game.
        
- ### Restart Game
        - When Restart Button pressed, player will be redirect to Main Menu.        

- ### Game Over
        - When player's heart decrease to 0, the screen will display Game Over and a Restart Button.

- ### Save Game
        - When keyboard button 'S' is pressed, the game progress will be saved.
        - Only one game progress can be saved at a time. If there is any progress saved before, the progress will be cover by the latest saved progress.


- ### Start New Game 

        - When Start a New Game button pressed, the game will start from level 1 and the ball will start dropping down.

- ### Cheese block

        - If Cheese block is destroyed, cheese will drop down from the position that the block is destroyed.
        - If player get the cheese, game will rewards 3 marks to player.
        - Change the design of block and cheese drop down.
        
- ### Initialize the paddle to the bottom and middle part of the screen

        - Once player start a new game, pass a level or restart level, the paddle will set position to the middle of the screen instead of the leftside of the screen.

- ### Initialize the position of ball to the middle of the screen
        - Once player start a new game, pass a level or restart level, the position of ball will rese to the middle of the scrren and start dropping right down.

- ### Gold ball
        - Change the ball image and background image when gold ball status.


## Implemented but Not Working Properly

- ### 
## Features Not Implemented
## New Java Classes
## Modified Java Classes
## Unexpected Problems
