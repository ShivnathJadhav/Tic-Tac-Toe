import Controller.GameController;
import Models.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("Hey!!!!!! Welcome to TicTacToe............");
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the diamention of the board?");
        int diamention = scanner.nextInt();

        System.out.println("What is the number of players? ");
        int numberOfPlayers = scanner.nextInt();
        List<Player> players = new LinkedList<>();

        System.out.println("will there be any Bot Y/N ?");
        String isBot = scanner.next();
        if(isBot.equals("Y")) {
            numberOfPlayers=numberOfPlayers-1;
            System.out.println("Please enter the name of the Bot");
            String botName = scanner.next();
            System.out.println("Please enter the symbol of the Bot");
            String botSymbol = scanner.next();
            System.out.println("Please enter the difficulty level of the bot Easy-1. Medium-2, Hard-3");
            int difficultyLevel = scanner.nextInt();

            players.add(new Bot (botSymbol.charAt(0),botName, BotDifficultyLevel.EASY));
        }

        for(int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Please enter the name of the player" + (i+1));
            String playerName = scanner.next();
            System.out.println("Please enter the symbol of the player" + (i+1)); //Assumption: Single character
            String playerSymbol = scanner.next();
            Player player = new Player(playerSymbol.charAt(0), playerName, PlayerType.HUMAN);
            players.add(player);
        }

        //Game game = Game.getbuilder().setDiamention(diamention).setPlayers(players).build();
        GameController gameController = new GameController();
        Game game = gameController.createGame(diamention, players);
        while (gameController.getGameStatus(game)==GameStatus.INPROGRESS){
            // TODO Play the game
            System.out.println("Current Board: ");
            gameController.displayBoard(game);
            System.out.println("");
            gameController.executeNextMove(game);
        }
        if(gameController.getGameStatus(game)==GameStatus.DRAW){
            System.out.println("Game has drawn");
        }
        else{
            System.out.println("Game has won by " + gameController.getWinnerName(game));
        }
    }
}