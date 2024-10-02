package Strategy;

import Models.Board;
import Models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order1GameWinningStrategy implements GameWinningStrategy {
    List<HashMap<Character, Integer>> rowSymbolCount = new ArrayList<>();
    List<HashMap<Character, Integer>> columnSymbolCount = new ArrayList<>();
    HashMap<Character, Integer> topLeftdiagonalSymbolCount = new HashMap<>();
    HashMap<Character, Integer> topRightdiagonalSymbolCount = new HashMap<>();

    public Order1GameWinningStrategy(int diamentions) {
        for(int i = 0; i < diamentions; i++) {
            rowSymbolCount.add(new HashMap<>());
            columnSymbolCount.add(new HashMap<>());
        }
    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        Character symbol = move.getPlayer().getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        int dimention = board.getBoard().size();

        if(!rowSymbolCount.get(row).containsKey(symbol)) {
            rowSymbolCount.get(row).put(symbol, 0);
        }
        rowSymbolCount.get(row).put(symbol, rowSymbolCount.get(row).get(symbol) + 1);

        if(!columnSymbolCount.get(col).containsKey(symbol)) {
            columnSymbolCount.get(col).put(symbol, 0);
        }
        columnSymbolCount.get(col).put(symbol, columnSymbolCount.get(col).get(symbol) + 1);

        if(row == col){
            if(!topLeftdiagonalSymbolCount.containsKey(symbol)) {
                topLeftdiagonalSymbolCount.put(symbol, 0);
            }
            topLeftdiagonalSymbolCount.put(symbol,topLeftdiagonalSymbolCount.get(symbol)+1);
        }

        if(row+col == dimention-1){
            if(!topRightdiagonalSymbolCount.containsKey(symbol)) {
                topRightdiagonalSymbolCount.put(symbol, 0);
            }
            topRightdiagonalSymbolCount.put(symbol,topRightdiagonalSymbolCount.get(symbol)+1);
        }
        if(rowSymbolCount.get(row).get(symbol)==dimention || columnSymbolCount.get(col).get(symbol)==dimention){
            return true;
        }
        if(row == col && topLeftdiagonalSymbolCount.get(symbol)==dimention){
            return true;
        }
        if(row+col==dimention-1 && topRightdiagonalSymbolCount.get(symbol)==dimention){
            return true;
        }

        return false;
    }
}
