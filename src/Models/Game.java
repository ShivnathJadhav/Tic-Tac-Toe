package Models;

import Exceptions.InvalidGameDiamentionException;
import Strategy.GameWinningStrategy;
import Strategy.Order1GameWinningStrategy;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private List<Move> moves;
    private List<Player> players;
    private int nextPlayerIndex;
    private GameStatus gameStatus;
    private Player winingplayer;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    private GameWinningStrategy gameWinningStrategy;

    public Player getWiningplayer() {
        return winingplayer;
    }

    public void setWiningplayer(Player winingplayer) {
        this.winingplayer = winingplayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public static Builder getbuilder() {
        return new Builder();
    }

    public void makeNextMove() {
        Player playerWhoMoves = players.get(nextPlayerIndex);
        System.out.println("It is "+ playerWhoMoves.getName()+"'s turn");
        Move move = playerWhoMoves.devcideMove();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
             board.applyMove(move);
             moves.add(move);

             // Check Winner
            if(gameWinningStrategy.checkWinner(board,move)){
                gameStatus=GameStatus.ENDED;
                winingplayer=playerWhoMoves;
            }
            nextPlayerIndex+=1;
            nextPlayerIndex %= players.size();
        }
        else {
            //throw an exception
        }

    }

    public static class Builder {
        private int diamention;
        private List<Player> players;

        public Builder setDiamention(int diamention) {
            this.diamention = diamention;
            return this;
        }
        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public Game build() {
            try{
                isValid();
            } catch (InvalidGameDiamentionException e){
                return null;
            }
            Game game = new Game();
            game.setBoard(new Board(diamention));
            game.setPlayers(players);
            game.setMoves(new LinkedList<>());
            game.setNextPlayerIndex(0);
            game.setGameStatus(GameStatus.INPROGRESS);
            game.setGameWinningStrategy(new Order1GameWinningStrategy(diamention));
            return game;
        }
        private boolean isValid() throws InvalidGameDiamentionException {
            if (diamention < 3) {
                throw new InvalidGameDiamentionException("Dimention should be greater than 2");
            }

            // ToDO to add more validation

            return true;
        }
    }
}