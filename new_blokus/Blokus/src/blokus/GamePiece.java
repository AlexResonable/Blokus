package blokus;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.geom.*;

public class GamePiece{
    public static final int BLANK_CELL = 0;
    public static final int CORNER_CELL = 1;
    public static final int ADJACENT_CELL = 2;
    public static final int PIECE_CELL = 3;
    public static final int SHAPE_CONTAINER_SIZE = 7;

    public static final int POINT_WEIGHT = 1;
    public static final int NUMBER_OF_PIECES = 21;
    //public static final int DEFAULT_RESOLUTION = 120;
    public static final int PIECE_PREVIEW_RESOLUTION = 40;

    private int[][] shapeContainer;
    private int shapeColor;
    private Boolean isUsed;

    public GamePiece(int[][] shape, int color){
        shapeContainer = (int[][]) shape.clone();
        shapeColor = color;
        isUsed = false;
    }

    public void rotateLeft(){
        int[][] temp = new int[SHAPE_CONTAINER_SIZE][SHAPE_CONTAINER_SIZE];
        for (int row = 0; row < SHAPE_CONTAINER_SIZE; row++)
            for (int col = 0; col < SHAPE_CONTAINER_SIZE; col++)
                temp[col][SHAPE_CONTAINER_SIZE - row - 1] = shapeContainer[row][col];

        shapeContainer = temp;
    }

    public void rotateRight(){
        int[][] temp = new int[SHAPE_CONTAINER_SIZE][SHAPE_CONTAINER_SIZE];
        for (int row = 0; row < SHAPE_CONTAINER_SIZE; row++)
            for (int col = 0; col < SHAPE_CONTAINER_SIZE; col++)
                temp[SHAPE_CONTAINER_SIZE - col - 1][row] = shapeContainer[row][col];

        shapeContainer = temp;
    }

    public Boolean isPieceUsed(){
        return isUsed;
    }

    public void setToUsed(){
        isUsed = true;
    }

    public void flip(){
        int[][] temp = new int[SHAPE_CONTAINER_SIZE][SHAPE_CONTAINER_SIZE];
        for(int row = 0; row < SHAPE_CONTAINER_SIZE; row++)
            for(int col = 0; col < SHAPE_CONTAINER_SIZE; col++)
                temp[SHAPE_CONTAINER_SIZE - row - 1][col] = shapeContainer[row][col];

        shapeContainer = temp;
    }

    public BufferedImage render(int size){
        int cellSize = size / (SHAPE_CONTAINER_SIZE);
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();

        if(isUsed){
            g.setColor(Color.WHITE);
            g.fill3DRect(0, 0, size, size,false);
        }
        else{
            g.setColor(Color.WHITE);
            g.fill3DRect(0, 0, size, size,true);
        }
        //g.fillRect(0, 0, size, size);

        for (int row = 0; row < SHAPE_CONTAINER_SIZE; row++){
            for (int col = 0; col < SHAPE_CONTAINER_SIZE; col++){
                if (shapeContainer[row][col] == PIECE_CELL){
                    BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                    //g.setStroke(bs1);
                    g.setPaint(new GradientPaint(0,0,Color.WHITE,100, 0,Color.GRAY));
                    g.setColor(Board.getColor(shapeColor));
                    
                    g.fill3DRect(row * cellSize, col * cellSize, cellSize, cellSize,true);
                    //g.fillRect(row * cellSize, col * cellSize, cellSize, cellSize);
                    //g.setColor(Board.getColor(shapeColor));
                    //g.setColor(Color.BLACK);
                    g.setPaint(new GradientPaint(0,0,Color.WHITE,100, 0,Color.GRAY));
                    g.drawRect(row * cellSize, col * cellSize, cellSize, cellSize);
                }
            }
        }

        return image;
    }

    public static int[][][] getAllShapes(){
        int[][][] shapes = new int[NUMBER_OF_PIECES][SHAPE_CONTAINER_SIZE][SHAPE_CONTAINER_SIZE];
        int i = 0;

////////////////////////////////////////////////////////////////////////////////
        // monomino
////////////////////////////////////////////////////////////////////////////////


        // *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

////////////////////////////////////////////////////////////////////////////////
        // domino
////////////////////////////////////////////////////////////////////////////////


        // * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

////////////////////////////////////////////////////////////////////////////////
        // triominoes
////////////////////////////////////////////////////////////////////////////////


        // *
        // * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 1, 0},
            {0, 0, 2, 3, 3, 2, 0},
            {0, 0, 1, 2, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // * * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 2, 1, 0},
            {0, 2, 3, 3, 3, 2, 0},
            {0, 1, 2, 2, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

////////////////////////////////////////////////////////////////////////////////
        // tetrominoes
////////////////////////////////////////////////////////////////////////////////


        //   *
        // * * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 1, 2, 3, 2, 1, 0},
            {0, 2, 3, 3, 3, 2, 0},
            {0, 1, 2, 2, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // * * *
        //     *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 2, 2, 0},
            {0, 2, 3, 3, 3, 2, 0},
            {0, 1, 2, 2, 3, 2, 0},
            {0, 0, 0, 1, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // * * * *
        shapes[i++] = new int[][] {
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        //   * *
        // * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 2, 1, 0},
            {0, 1, 2, 3, 3, 2, 0},
            {0, 2, 3, 3, 2, 1, 0},
            {0, 1, 2, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // * *
        // * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 1, 0, 0},
            {0, 2, 3, 3, 2, 0, 0},
            {0, 2, 3, 3, 2, 0, 0},
            {0, 1, 2, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

////////////////////////////////////////////////////////////////////////////////
        // pentominoes
////////////////////////////////////////////////////////////////////////////////


        // * * * * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {1, 2, 2, 2, 2, 2, 1},
            {2, 3, 3, 3, 3, 3, 2},
            {1, 2, 2, 2, 2, 2, 1},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // *
        // * * * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 1, 0, 0, 0},
            {0, 2, 3, 2, 2, 2, 1},
            {0, 2, 3, 3, 3, 3, 2},
            {0, 1, 2, 2, 2, 2, 1},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // *
        // *
        // * *
        //   *
        shapes[i++] = new int[][] {
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 2, 3, 2, 1, 0},
            {0, 0, 2, 3, 3, 2, 0},
            {0, 0, 1, 2, 3, 2, 0},
            {0, 0, 0, 1, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        //   *
        // * * * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 1, 2, 3, 2, 2, 1},
            {0, 2, 3, 3, 3, 3, 2},
            {0, 1, 2, 2, 2, 2, 1},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        //   *
        // * * *
        //     *
        shapes[i++] = new int[][] { 
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 1, 2, 3, 2, 1, 0},
            {0, 2, 3, 3, 3, 2, 0},
            {0, 1, 2, 2, 3, 2, 0},
            {0, 0, 0, 1, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        //   *
        // * * *
        //   *
        shapes[i++] = new int[][] { 
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 1, 2, 3, 2, 1, 0},
            {0, 2, 3, 3, 3, 2, 0},
            {0, 1, 2, 3, 2, 1, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // * * *
        // *   *
        shapes[i++] = new int[][] { 
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 2, 1, 0},
            {0, 2, 3, 3, 3, 2, 0},
            {0, 2, 3, 2, 3, 2, 0},
            {0, 1, 2, 1, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // * * *
        //   * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 2, 1, 0},
            {0, 2, 3, 3, 3, 2, 0},
            {0, 1, 2, 3, 3, 2, 0},
            {0, 0, 1, 2, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        //     *
        //   * *
        // * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 2, 1, 0},
            {0, 0, 1, 2, 3, 2, 0},
            {0, 1, 2, 3, 3, 2, 0},
            {0, 2, 3, 3, 2, 1, 0},
            {0, 1, 2, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        //   *
        //   *
        // * * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 1, 2, 3, 2, 1, 0},
            {0, 2, 3, 3, 3, 2, 0},
            {0, 1, 2, 2, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // *
        // *
        // * * *
        shapes[i++] = new int[][] {
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 2, 3, 2, 2, 1},
            {0, 0, 2, 3, 3, 3, 2},
            {0, 0, 1, 2, 2, 2, 1},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        //   * *
        //   *
        // * *
        shapes[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 2, 1, 0},
            {0, 0, 2, 3, 3, 2, 0},
            {0, 1, 2, 3, 2, 1, 0},
            {0, 2, 3, 3, 2, 0, 0},
            {0, 1, 2, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        return shapes;
    }

    public int getValue(int row, int col){
        return shapeContainer[row][col];
    }

    public int getColor(){
        return shapeColor;
    }

    public int getTotalPoints(){
        int totalPoints = 0;
        for (int row = 0; row < SHAPE_CONTAINER_SIZE; row++)
            for (int col = 0; col < SHAPE_CONTAINER_SIZE; col++)
                if (shapeContainer[row][col] == PIECE_CELL)
                    totalPoints++;
        return totalPoints * POINT_WEIGHT;
    }

    public int getContainerSize(){
        return SHAPE_CONTAINER_SIZE;
    }

    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        for (int row = 0; row < SHAPE_CONTAINER_SIZE; row++){
            for (int col = 0; col < SHAPE_CONTAINER_SIZE; col++){
                stringBuffer.append(shapeContainer[row][col]);
                stringBuffer.append(" ");
            }
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }
}