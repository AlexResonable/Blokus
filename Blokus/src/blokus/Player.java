package blokus;
import java.util.LinkedList;

public class Player{
    public LinkedList<GamePiece> pieces;
    public boolean canPlay = true;
    public boolean firstMove = true;
    
    public String name;

    public Player(int color){
        int[][][] shapes = GamePiece.getAllShapes();

        pieces = new LinkedList<GamePiece>();

        for (int i = 0; i < shapes.length; i++){
            pieces.add(new GamePiece(shapes[i], color));
        }
    }
    
    public Player(int color, String playerName){
        int[][][] shapes = GamePiece.getAllShapes();

        pieces = new LinkedList<GamePiece>();

        for (int i = 0; i < shapes.length; i++){
            pieces.add(new GamePiece(shapes[i], color));
        }
        
        name = playerName;
    }
    
    public void setName(String playerName){
        name = playerName;
    }

    public int getTotalScore(){
        int total = 0;

        for(GamePiece gamePiece : pieces){
            if(gamePiece.isPieceUsed())
                total += gamePiece.getTotalPoints();
        }
        return total;
    }
}
