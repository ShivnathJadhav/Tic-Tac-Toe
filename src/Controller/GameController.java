package Controller;

import Models.Game;
import Models.GameStatus;
import Models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimention, List<Player> players){
        return Game.getbuilder()
                .setDiamention(dimention)
                .setPlayers(players)
                .build();
    }
    public GameStatus getGameStatus (Game game){
        return game.getGameStatus();
    }
    public String getWinnerName(Game game){
        return game.getWiningplayer().getName();
    }
    public void setGameStatus(Game game, GameStatus gameStatus){
        game.setGameStatus(gameStatus);
    }

    public void displayBoard(Game game) {
        game.getBoard().displayBoard();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }
}