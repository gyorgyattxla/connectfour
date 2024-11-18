package attilaprojects;

import attilaprojects.gamefield.GameField;

import attilaprojects.logic.GameLoop;


public class Main
{
    public static void main(String[] args ) {

        //Initialize gameField
        GameField gameField = new GameField();

        GameLoop gameLoop = new GameLoop(gameField);

        gameLoop.gameLoop();
    }
}

