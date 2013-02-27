package blokusgame;

import java.util.LinkedList;

public class Player{
    public LinkedList<GamePiece> pieces;
    public boolean firstMove = true;
    public boolean canPlay = true;

    public Player(int color)
    {
        int[][][] shapes = GamePiece.getAllShapes();

        pieces = new LinkedList<GamePiece>();

        for (int i = 0; i < shapes.length; i++){
            pieces.add(new GamePiece(shapes[i], color));
        }
    }

    public int getScore(){
        int total = 0;

        for(GamePiece bp : pieces){
            total += bp.getPoints();
        }
        return total;
    }
}
