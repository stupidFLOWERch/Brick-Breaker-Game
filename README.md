
# Brick Breaker Game 🧱

Brick Breaker Game is a classic retro video game. The player's goal is to destroy all the bricks in the screen. using a paddle and a bouncing ball. Here are the simple command:

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

- ### Paddle Extension

        - I want to make the paddle's width extends when the ball destroyed specific type of block. However when I try to work with the features, it does not work properly.
        - If the ball destroyed the specific type of brick, the paddle's width should be extended. However when i destroyed the block, the image of paddle displayed on the screen is not extended but behind the screen it is extended.
        - This cause the paddle image and paddle's real width does not match and player can catch the ball although the paddle shows in the screen did not hit the ball.
        - The features already removed from the game.

- ### Freeze the movement of ball and paddle at the start of game
        - At the beginning, this feature is implemented but does not work properly. The ball freeze and when I press on Space it will start dropping down to the paddle.
        - However when the ball hit the paddle, the speed of the ball will become very fast which should not be happened.
        - The features is now removed from game.

- ### Reset the position of ball and freeze it when loss 1 heart
        - This feature is implemented but it had same problem with the previous feature.
        - After pressing Space, the speed of the ball become too fast when hit to the paddle from dropping down.
        - This features is removed.


## Features Not Implemented

- ### Level Selection
        - Level selection feature is more designed for games with many levels.
        - The feature is left out because this game only have 17 levels and 1 bonus level. I think this is not the feature with the higher priority.

- ### Timer for challenge level
        - This features is mainly aim to motivate players to try their best to to pass a level in time given.
        - However, due to the complexity of implementing a fair timer system, I decide to prioritize other features that can improve player experience.

- ### Iron block
        - Create iron blocks that require two hits to be destroyed from the screen.
        - This is a good features to increase fun of the game. However, the idea appeared during the final stages of the game development.
        - Thus, this features is left out as there is not enough timeto add it to the game.


## New Java Classes

- ### BallObject.Java
        - This Java class represents a ball in a game, containing properties such as position, direction, collision flags, velocities, and timing information.

- ### BlockObject.java
        - This Java class manages collections of blocks, bonuses (cheeses), and traps in a game, each represented by specific shapes and colors.

- ### Trap.java
        - This Java class represents a serializable trap object in a game, featuring a rectangular shape with an associated image, positioned based on specified row and column parameters, and initialized with random trap images.

- ### BreakObject.java
        - This Java class represents a break object in a game, defining its position, dimensions, movement capabilities, and associated rectangle shape for collision detection.

- ### InstructionMenu.java
        - This Java class is responsible for creating the layout of the instruction menu in a game, featuring a back button to return to the main menu and incorporating components related to the game's main objects (BallObject, BreakObject, BlockObject, LevelObject).

- ### MainMenu.java
        - This Java class represents the main menu of a game and provides options for load game, start a new game, accessing instructions, viewing the high score page, and exiting the game.  
        - It incorporates functionality for handling game states, loading/saving, and navigation between different sections of the game.

- ### PauseGame.java
        - This Java class manages the pause state of the game, providing functionality to toggle the pause status and reset it to an unpaused state. The isPaused variable keeps track of whether the game is currently paused.

- ### PauseMenu.java
        - This Java class represents a pause menu in a game, providing options to resume the game, exit to the main menu, and restart the current level. It utilizes JavaFX components and is designed to be displayed when the game is paused. The class includes methods to handle the respective actions upon button clicks.

- ### ShowPauseMenu.java
        - This Java class is responsible for displaying and hiding the pause menu in a game, as well as managing actions such as resuming the game or exiting the application. It interacts with various components of the game, including the main game class (Main), the pause menu itself, and the game engine (GameEngine).
        - The class incorporates methods to show and hide the pause menu, resume the game, and exit the application.

- ### LevelObject.java
        - This Java class represents the state and attributes of a game level, including the current level number, remaining hearts, player score, gold status, existence of heart blocks, count of destroyed blocks, and various labels for displaying information. 
        - It manages information related to restarting the game, loading from a saved state, and handles scores, levels, and file paths for high scores. The class encapsulates key parameters and labels relevant to the game's progression and scoring system.

- ### RestartLevel.java
        - This Java class contains a method restartLevel designed to reset and restart the game level in response to a restart action. It takes parameters from the main game class (Main), the ball object (BallObject), the block object (BlockObject), and the level object (LevelObject). The method sets various attributes and flags to their initial or default states, clears existing blocks, cheeses, and traps, and then proceeds to restart the game using the StartGame class. 
        - It utilizes the ResetCollideFlags class to reset collision flags associated with the ball.

- ### HighScorePage.java
        - This Java class is responsible for displaying the high score page in a game. It creates a Pane with congratulatory labels, player name, high score, and a button to return to the main menu. The class utilizes the LoadHighScore class to retrieve and display high score information. 
        - The appearance of the page is stylized using CSS classes, and the show method is used to present the high score page on the game scene.

- ### SaveHighScore.java
        - This Java class, named SaveHighScore, contains a method saveHighScore that is responsible for saving a player's high score to a file. It takes two parameters - the player's name and their current score. 
        - The method creates a BufferedWriter to append the player's name and score to a file specified by the LevelObject class. The file path is obtained from the getFilePath method in the LevelObject class, and the file is located in the "C:\high" directory. If the directory doesn't exist, the method creates it before saving the high score entry. If an IOException occurs during the writing process, the exception is printed to the standard error stream.

- ### LoadHighScore.java
        - This Java class provides methods to retrieve the highest score and corresponding player name from a file containing high score entries. The file path is obtained from the LevelObject class.

- ### Bgm.java
        - The Bgm class is a Java class that provides functionality for handling background music in a game using Java's javax.sound.sampled library.

- ### Sound.java
        - The Sound class handles sound effects by using the javax.sound.sampled library, including a SoundEffect inner class that manages loading and playing the specified sound effect file.

- ### Win.java
        - The Win class is designed to play a win sound effect using the javax.sound.sampled library, with a nested SoundEffect class handling the loading and playing of the specified sound file when an instance of the Win class is created.
## Modified Java Classes
- ### InitBall.java
        - This class is created by moving the initball method from Main.java to it.
    - #### In method initBall

            - Change the initialization of xBall and yBall from random to a fixed position.

            - Make the code better and easier for management.

- ### ResetCollideFlags.java
        - Move the resetCollideFlags method from Main.java to new class ResetCollideFlags.java.

        - Make the code better and easier for management.

- ### SetPhysicsToBall.java
        - Move method setPhysicsToBall from Main.java to new class SetPhysicsToBall.
    - #### In method setPhysicsToBall

            - Correct the typo colide to collide

            - Plus or minus ball radius to the condition that check the ball hit to the wall

            - Add condition to let the GoldStatus true if current level is bonus level

            - Remove ununsed comment 


- ### Block.java 
        - Add Platform.runLater() to the part update the UI of game
    - #### In method checkHitToBlock

            - Modify the condition for method checkHitToBlock

            - Create variable to use for checking the ball hit to which direction of block

            - To improve the collision of ball with block and improve the UI display
    - #### In method draw

            - Add BLOCK_TRAP's pattern to be generate
        

- ### Bonus.java
    - #### In method initializeCheese

            - Change choco to cheese

            - Cater to the theme of game


- ### CheckDestroyedCount.java
        - Move method checkDestroyedCount from Main.java to new class CheckDestroyedCount.java 
    - #### In method checkDestroyedCount

            - Add condition to rewards player if player pass a level with full health

            -  Make the code better and easier for management.


- ### InitBlock.java
        - Move method initBlock from Main.java to new class InitBlock.java
    - #### In method initBlock
            - Add part to generate BLOCK_TRAP

            - Add bonus level that generate all block as BLOCK_CHEESE
        

- ### InitBreak.java
        - Move method InitBreak from Main.java to new class InitBreak.java
    - #### In method initBreak

            - Set the position of paddle to the middle of the screen in method initBreak.

- ### GameEngine.java
        - Replace threads by the built-in animation loop provided by AnimationTimer

        - This reduce the chance of concurrency issues.

        - Remove the method setFps

    - #### Animation Timer
            -The use of AnimationTimer simplifies the game loop implementation. The handle method of AnimationTimer is called in each frame, eliminating the need for manual thread management.
            
- ### Handle.java
        - Move method handle() from Main.java to new class Handle.java

    - #### In method handle

            - Add case 'P' to the code and its if-else condition to pause or unpause the game

- ### Move.java
        - Move method move from Main.java to new class Move.java

    - #### In method move
            - Change the condition to check if the paddle reach to the edge of the screen

- ### OnAction.java
        - Move method onUpdate, method onPhysicsUpdate and method onTime from Main.java to new class OnAction.java
    
    - #### In method onUpdate   
            - Add code to generate the drop down mousetrap after destroyed the BLOCK_TRAP
            
            - Create and modify a copy of the list blocksCopy instead of the original list

            - To prevents potential concurrent modification issues
            
            - Relies on the getter methods of the main object. 

    - #### In method onPhysicsUpdate
            - Uses iterators to iterate over the lists of Bonus and Trap objects

            - Remove elements from the lists during iteration to prevents ConcurrentModificationException that could occur 

            - Creates separate lists to store objects that need to be removed. After iteration, the objects are removed using removeAll method. 
            
            - This avoid issues with modifying a list during iteration.


- ### Score.java
        - Add Platform.runLater() to all the part related to update game UI
        
        - Create constants for animation duration and delay to improve code readability and allow for easy modification.

        - Instances of related classes (SaveHighScore, LoadHighScore, RestartGame, LoadSave) are created and used to promote a more modular design.

        - Add method showCongrat to congrats player pass the game

    - #### In method showWin
            - Add bonus level button and restart button

            - Add code to check whether player achieve the highest score 

            - Display dialog box to let user input name if highest score is achieved

    - #### In method showGameOver
            - Add code to check whether player achieve the highest score 

            - Display dialog box to let user input name if highest score is achieved

- ### NextLevel.java
        - Move method nextLevel from Main.java to new class NextLevel.java

    - #### In method nextLevel
            - Instances of related classes (StartGame and ResetCollideFlags) are created within the method to promote a more modular design.

            - Use getter and setter methods to access and modify the state of the LevelObject 

            - Call GameEngine.setPaused(true) to pause the game engine to avoid direct access to static variables.

            - Add condition to check for level 18 and displays a congratulatory message using the Score class if true.

            - Set GoRightBall as true

- ### RestartGame.java
        - Move restartGame method from Main.java to new class RestartGame.java

    - #### Method restartGame
            - Use getter and setter methods to access and modify the state of LevelObject, BallObject and BlockObject

            - Set GoRightBall as true and GetHeart as false

            - Clear Traps and call method clearBlocks() 

- ### SaveGame.java
        - Move saveGame method from Main.java to new class SaveGame.java
        
        - Change the savePath and savePathDir

        - Modify the value to be save 

        - Use getter and sstter method to access LevelObject, BallObject, BlockObject and BreakObject

- ### LoadGame.java
        - Move loadGame method from Main.java to new class LoadGame.java

        - Modify the value to be load

        - Use getter and sstter method to access LevelObject, BallObject, BlockObject and BreakObject

- ### LoadSave.java
        - Create method checkfile to check existing of saved file

        - Create method initializeBonusObjects to initialize bonus objects

- ### StartGame.java
    - #### startGame method
            - Split and move the start method in Main.java to new class StartGame.java

            - Change the logic for level up and win the game 

            - Set the ball to a fixed position

            - Use getter and setter method to access and modify the state of LevelObject, BallObject and BlockObject

- ### Main.java
        - Remove unnecessary variable

         - Create getter and setter method
        
    - #### In start method
            - Create instances of game object

            - Create instance of MainMenu class, pass the references to various game objects. 
            
            - Initialize some variable by setter methods

            - Move all method to others class to make code easier for manageable and readable.

            
## Unexpected Problems

