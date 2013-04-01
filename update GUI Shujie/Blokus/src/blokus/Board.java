package blokus;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import javax.imageio.ImageIO;

public class Board {
    public static final int NONE = 0;
    public static final int BLUE = 1;
    public static final int RED = 2;
    public static final int ORANGE = 3;
    public static final int GREEN = 4;

    public static final int BOARD_SIZE = 20;

    public static final int DEFAULT_RESOLUTION = 500;

    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;
    public static final int PLAYER3 = 3;
    public static final int PLAYER4 = 4;

    public static final Color BOARD_COLOR = Color.WHITE;
    public static final Color GRID_COLOR = Color.GRAY;

    public static final String OFF_BOARD_ERROR = "Game Piece must be placed on the board.";
    public static final String ADJACENCY_ERROR = "Game Pieces of the same color cannot share edges with one another.";
    public static final String OVERLAP_ERROR = "Game Pieces cannot overlap.";
    public static final String START_ERROR = "Starting Game peice must occupy the player's respective colored corner.";
    public static final String CORNER_ERROR = "Game Pieces must be connected to at least one other piece of the the same color by the corner.";

    private int[][] boardGrid;
    private int[][] boardOverlay;

    public Board(){
        boardGrid = new int[BOARD_SIZE][BOARD_SIZE];
        boardOverlay = new int[BOARD_SIZE][BOARD_SIZE];
        initialize(boardGrid);
        initialize(boardOverlay);
    }

    public Board(int[][] board){
        boardGrid = board;
        boardOverlay = new int[BOARD_SIZE][BOARD_SIZE];
    }

    private void initialize(int[][] array){
        for (int row = 0; row < BOARD_SIZE; row++)
            for (int col = 0; col < BOARD_SIZE; col++)
                array[row][col] = NONE;
    }

    public boolean isValidMove(GamePiece piece, int xOffset, int yOffset, boolean firstMove) throws IllegalMoveException{
        boolean corner = false;
        for (int x = 0; x < piece.getContainerSize(); x++){
            for (int y = 0; y < piece.getContainerSize(); y++){
                int value = piece.getValue(x, y);
                boolean inBounds = isInBounds(x + xOffset, y + yOffset);

                if(inBounds){
                    int gridValue = boardGrid[x + xOffset][y + yOffset];

                    if(gridValue != NONE){
                        if (value == GamePiece.PIECE_CELL)
                            throw new IllegalMoveException(OVERLAP_ERROR);
                        if (gridValue == piece.getColor()){
                            if (value == GamePiece.ADJACENT_CELL)
                                throw new IllegalMoveException(ADJACENCY_ERROR);
                            if (value == GamePiece.CORNER_CELL)
                                corner = true;
                        }
                    }
                    else{
                        if(firstMove && value == GamePiece.PIECE_CELL && new Point(x + xOffset, y + yOffset).equals(getCorner(piece.getColor())))
                            corner = true;
                    }
                }
                else{
                    if (value == GamePiece.PIECE_CELL) throw new IllegalMoveException(OFF_BOARD_ERROR);
                }
            }
        }

        if (!corner)
            throw new IllegalMoveException(firstMove ? START_ERROR : CORNER_ERROR);
        return true;
    }

    public boolean isValidMove(GamePiece bp, int xOffset, int yOffset) throws IllegalMoveException{
        return isValidMove(bp, xOffset, yOffset, false);
    }

    public void placePiece(GamePiece piece, int xOff, int yOff, boolean firstMove) throws IllegalMoveException{
        isValidMove(piece, xOff, yOff, firstMove);

        for(int x = 0; x < piece.getContainerSize(); x++){
            for(int y = 0; y < piece.getContainerSize(); y++){
                if(piece.getValue(x, y) == GamePiece.PIECE_CELL)
                    boardGrid[x + xOff][y + yOff] = piece.getColor();
            }
        }
    }

    public void placePiece(GamePiece bp, int xOff, int yOff) throws IllegalMoveException{
        placePiece(bp, xOff, yOff, false);
    }

    public Point getCoordinates(Point pixel, int res){
        return new Point(pixel.x / (res / BOARD_SIZE), pixel.y / (res / BOARD_SIZE));
    }

    public void overlay(GamePiece piece, int xOff, int yOff){
        initialize(boardOverlay);

        for (int x = 0; x < piece.getContainerSize(); x++){
            for (int y = 0; y < piece.getContainerSize(); y++){
                if (isInBounds(x + xOff - piece.getContainerSize() / 2, y + yOff - piece.getContainerSize() / 2) && piece.getValue(x, y) == GamePiece.PIECE_CELL){
                    boardOverlay[x + xOff - piece.getContainerSize() / 2][y + yOff - piece.getContainerSize() / 2] = piece.getColor();
                }
            }
        }
    }

    public BufferedImage render(){
        return render(DEFAULT_RESOLUTION);
    }

    public BufferedImage render(int size){
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        int cellSize = size / (BOARD_SIZE);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setBackground(new Color(0,0,0,5));
        Image img = Toolkit.getDefaultToolkit().createImage("blokus.gif");
        //g.drawImage(img, 0, 0, null);
         g.drawImage(img, DEFAULT_RESOLUTION/3, DEFAULT_RESOLUTION/3, null);
        for (int x = 0; x < BOARD_SIZE; x++){
            for (int y = 0; y < BOARD_SIZE; y++){
       
                g.setColor(getColor(boardGrid[x][y]));

                if (boardOverlay[x][y] != NONE){
                    g.setColor(blend(g.getColor(), getColor(boardOverlay[x][y]), 0.1f));
                    //g.setColor(blend(new Color(0,0,0,5), getColor(boardOverlay[x][y]), 0.1f));
                }

                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                g.setColor(GRID_COLOR);
                //g.setColor(Color.BLACK);
                g.draw3DRect(x * cellSize, y * cellSize, cellSize, cellSize,true);

                if (boardGrid[x][y] == NONE){
                    boolean corner = false;
                    Point p = new Point(x, y);
                    
                    if (getCorner(BLUE).equals(p)){
                        g.setColor(getColor(BLUE));
                        corner = true;
                    }
                    else if (getCorner(RED).equals(p)){
                        g.setColor(getColor(RED));
                        corner = true;
                    }
                    else if (getCorner(GREEN).equals(p)){
                        g.setColor(getColor(GREEN));
                        corner = true;
                    }
                    else if (getCorner(ORANGE).equals(p)){
                        g.setColor(getColor(ORANGE));
                        corner = true;
                    }
                    if (corner){
                        g.fill3DRect(x * cellSize + cellSize / 2 - cellSize / 6, y * cellSize + cellSize / 2 - cellSize / 6, cellSize / 3, cellSize / 3, true);
                    }
                }
            }
        }
        return image;
    }

    private Color blend(Color c1, Color c2, float balance){
        int r = (int)(c1.getRed() * balance + c2.getRed() * (1 - balance));
        int g = (int)(c1.getGreen() * balance + c2.getGreen() * (1 - balance));
        int b = (int)(c1.getBlue() * balance + c2.getBlue() * (1 - balance));
        return new Color(r, g, b);
    }

    public void initializeOverlay(){
        initialize(boardOverlay);
    }

    private boolean isInBounds(int row, int col){
        return (row >= 0 && col >= 0 && row < BOARD_SIZE && col < BOARD_SIZE);
    }

    public Point getCorner(int color){
        switch (color){
            case BLUE: return new Point(0, 0);
            case GREEN: return new Point(0, BOARD_SIZE - 1);
            case ORANGE: return new Point(BOARD_SIZE - 1, BOARD_SIZE - 1);
            case RED: return new Point(BOARD_SIZE - 1, 0);
            default: throw new IllegalArgumentException();
        }
    }

    public static Color getColor(int color){
        switch (color){
            case BLUE: return Color.BLUE;
            case RED: return Color.RED;
            case ORANGE: return Color.ORANGE;
            case GREEN: return new Color(0, 128, 0);
            default: return BOARD_COLOR;
        }
    }

    public static String getColorName(int color){
        switch (color){
            case BLUE: return "Blue";
            case RED: return "Red";
            case ORANGE: return "Orange";
            case GREEN: return "Green";
            default: return "Unknown";
        }
    }

    public static class IllegalMoveException extends Exception{
        public IllegalMoveException(){
            super();
        }

        public IllegalMoveException(String message){
            //super(message);
        }
    }
}


