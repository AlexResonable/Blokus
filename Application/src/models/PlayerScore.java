/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Stacy
 */
public class PlayerScore {
    private String name;
    private int score;
    
    public PlayerScore()
    {
        name = "";
        score = 1000000000;
    }
    public PlayerScore(String n, int s)
    {
        name = n;
        score = s;
        
    }
    
    public void setName(String n)
    {
        name = n;
    }
    public void setScore(int s)
    {
        score = s;
    }
    public String getName()
    {
        return name;
    }
    
    public int getScore()
    {
        return score;
    }
    
    
    @Override
    public String toString()
    {
        return "" + this.name + " " + this.score + "";
    }
}
