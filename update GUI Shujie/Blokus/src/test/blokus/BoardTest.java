/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blokus;

import blokus.Board;
import blokus.Board.IllegalMoveException;
import blokus.GamePiece;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kamijean
 */
public class BoardTest {
    int shape[][] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 2, 3, 2, 0, 0},
            {0, 0, 1, 2, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
    public BoardTest() {
    }

    /**
     * Test of isValidMove method, of class Board.
     */
    @Test
    public void testIsValidMove_4args() throws Exception {
        System.out.println("isValidMove");
        GamePiece piece = new GamePiece(shape, 1);
        int xOffset = 0;
        int yOffset = 0;
        boolean firstMove = false;
        Board instance = new Board();
        boolean expResult = true;
        boolean result = false;
        try{
            result = instance.isValidMove(piece, xOffset, yOffset, firstMove);
            assertEquals(expResult, result);
        }
        catch(IllegalMoveException e){
            expResult = false;
            assertEquals(expResult, result);
        }
        
    }

    /**
     * Test of isValidMove method, of class Board.
     */
    @Test
    public void testIsValidMove_3args() throws Exception {
        System.out.println("isValidMove");
        GamePiece bp = new GamePiece(shape, 1);
        int xOffset = 0;
        int yOffset = 0;
        Board instance = new Board();
        boolean expResult = true;
        boolean result = false;
        try{
            result = instance.isValidMove(bp, xOffset, yOffset);
            assertEquals(expResult, result);
        }
        catch(IllegalMoveException e){
            expResult = false;
            assertEquals(expResult, result);
        }    
    }

    /**
     * Test of placePiece method, of class Board.
     */
    @Test
    public void testPlacePiece_4args() throws Exception {
        System.out.println("placePiece");
        GamePiece piece = new GamePiece(shape, 1);
        int xOff = 0;
        int yOff = 0;
        boolean firstMove = false;
        Board instance = new Board();
        try{
            instance.placePiece(piece, xOff, yOff, firstMove);
        }
        catch(IllegalMoveException e){
            
        }
    }

    /**
     * Test of placePiece method, of class Board.
     */
    @Test
    public void testPlacePiece_3args() throws Exception {
        System.out.println("placePiece");
        GamePiece bp = new GamePiece(shape, 1);;
        int xOff = 0;
        int yOff = 0;
        Board instance = new Board();
        try{
            instance.placePiece(bp, xOff, yOff);
        }
        catch(IllegalMoveException e){
            
        }
     
    }

    /**
     * Test of getCoordinates method, of class Board.
     */
    @Test
    public void testGetCoordinates() {
        System.out.println("getCoordinates");
        Point pixel = new Point(2,2);
        int res = 40;
        Board instance = new Board();
        Point expResult = new Point(1,1);
        Point result = instance.getCoordinates(pixel, res);
        assertEquals(expResult, result);
    }

    /**
     * Test of overlay method, of class Board.
     */
    @Test
    public void testOverlay() {
        System.out.println("overlay");
        GamePiece piece = new GamePiece(shape, 1);
        int xOff = 0;
        int yOff = 0;
        Board instance = new Board();
        instance.overlay(piece, xOff, yOff);
    }


    /**
     * Test of initializeOverlay method, of class Board.
     */
    @Test
    public void testInitializeOverlay() {
        System.out.println("initializeOverlay");
        Board instance = new Board();
        instance.initializeOverlay();
    }

    /**
     * Test of getColor method, of class Board.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        int color = 0;
        Color expResult = new Color(255,255,255);
        Color result = Board.getColor(color);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColorName method, of class Board.
     */
    @Test
    public void testGetColorName() {
        System.out.println("getColorName");
        int color = 0;
        String expResult = "Unknown";
        String result = Board.getColorName(color);
        assertEquals(expResult, result);
        color = 1;
        expResult = "Blue";
        result = Board.getColorName(color);
        assertEquals(expResult, result);
        color = 1;
        expResult = "Blue";
        result = Board.getColorName(color);
        assertEquals(expResult, result);
        color = 2;
        expResult = "Red";
        result = Board.getColorName(color);
        assertEquals(expResult, result);
        color = 3;
        expResult = "Orange";
        result = Board.getColorName(color);
        assertEquals(expResult, result);
        color = 4;
        expResult = "Green";
        result = Board.getColorName(color);
        assertEquals(expResult, result);
    }
}
