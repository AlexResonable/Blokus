/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blokus;

import java.awt.image.BufferedImage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import blokus.GamePiece;
import static org.junit.Assert.*;


public class GamePieceTest {
    
    public GamePieceTest() {
    }
    int shape[][] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
        int shape2[][] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 1, 2, 3, 2, 1, 0},
            {0, 2, 3, 3, 3, 2, 0},
            {0, 1, 2, 2, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

    /**
     * Test of rotateLeft method, of class GamePiece.
     */
    @Test
    public void testRotateLeft() {
        System.out.println("rotateLeft");
        GamePiece instance = new GamePiece(shape, 1);
        instance.rotateLeft();
    }

    /**
     * Test of rotateRight method, of class GamePiece.
     */
    @Test
    public void testRotateRight() {
        System.out.println("rotateRight");
        GamePiece instance = new GamePiece(shape, 1);
        instance.rotateRight();
    }

    /**
     * Test of isPieceUsed method, of class GamePiece.
     */
    @Test
    public void testIsPieceUsed() {
        System.out.println("isPieceUsed");
        GamePiece instance = new GamePiece(shape, 1);;
        Boolean expResult = false;
        Boolean result = instance.isPieceUsed();
        assertEquals(expResult, result);
    }

    /**
     * Test of setToUsed method, of class GamePiece.
     */
    @Test
    public void testSetToUsed() {
        System.out.println("setToUsed");
        GamePiece instance = new GamePiece(shape, 1);
        instance.setToUsed();
    }

    /**
     * Test of flip method, of class GamePiece.
     */
    @Test
    public void testFlip() {
        System.out.println("flip");
        GamePiece instance = new GamePiece(shape, 1);;
        instance.flip();
    }

    /**
     * Test of getAllShapes method, of class GamePiece.
     */
    @Test
    public void testGetAllShapes() {
        System.out.println("getAllShapes");
        int i = 0;
        int[][][] expResult = new int[21][][];

////////////////////////////////////////////////////////////////////////////////
        // monomino
////////////////////////////////////////////////////////////////////////////////


        // *
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 1, 0},
            {0, 0, 2, 3, 3, 2, 0},
            {0, 0, 1, 2, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // * * *
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 2, 2, 2, 0},
            {0, 2, 3, 3, 3, 2, 0},
            {0, 1, 2, 2, 3, 2, 0},
            {0, 0, 0, 1, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        // * * * *
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] { 
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
        expResult[i++] = new int[][] { 
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
        expResult[i++] = new int[][] { 
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
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
        expResult[i++] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 2, 1, 0},
            {0, 0, 2, 3, 3, 2, 0},
            {0, 1, 2, 3, 2, 1, 0},
            {0, 2, 3, 3, 2, 0, 0},
            {0, 1, 2, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
        
        int[][][] result = GamePiece.getAllShapes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class GamePiece.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        int row = 0;
        int col = 0;
        GamePiece instance = new GamePiece(shape, 1);
        int expResult = 0;
        int result = instance.getValue(row, col);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColor method, of class GamePiece.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        GamePiece instance = new GamePiece(shape, 1);
        int expResult = 1;
        int result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalPoints method, of class GamePiece.
     */
    @Test
    public void testGetTotalPoints() {
        System.out.println("getTotalPoints");
        GamePiece instance = new GamePiece(shape, 1);
        int expResult = 1;
        int result = instance.getTotalPoints();
        assertEquals(expResult, result);
        GamePiece instance2 = new GamePiece(shape2, 1);
        int expResult2 = 5;
        int result2 = instance2.getTotalPoints();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getContainerSize method, of class GamePiece.
     */
    @Test
    public void testGetContainerSize() {
        System.out.println("getContainerSize");
        GamePiece instance = new GamePiece(shape, 1);
        int expResult = 7;
        int result = instance.getContainerSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class GamePiece.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        GamePiece instance = new GamePiece(shape, 1);
        String expResult = "0 0 0 0 0 0 0 \n"
                + "0 0 0 0 0 0 0 \n"
                + "0 0 1 2 1 0 0 \n"
                + "0 0 2 3 2 0 0 \n"
                + "0 0 1 2 1 0 0 \n"
                + "0 0 0 0 0 0 0 \n"
                + "0 0 0 0 0 0 0 \n";
        //String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
