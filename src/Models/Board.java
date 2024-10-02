package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private  int size;
    private List<List<Cell>> cells;

    public Board(int diamention) {
        this.size = diamention;
        this.cells = new LinkedList<>();
        for(int i=0;i<diamention;i++){
            this.cells.add(new LinkedList<>());
            for(int j=0;j<diamention;j++){
                this.cells.get(i).add(new Cell(i,j));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public void displayBoard() {
        for(int i=0;i< cells.size();i++){
            for(int j=0;j<cells.size();j++){
                if(cells.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    System.out.print("|   |");
                }
                else{
                    System.out.print("| " + cells.get(i).get(j).getPlayer().getSymbol()+" |");
                }
            }
            System.out.println();
        }
    }

    public List<List<Cell>> getBoard() {
        return cells;
    }

    public void applyMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        this.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        this.getBoard().get(row).get(col).setPlayer(move.getPlayer());
    }
}
