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
    private int id;
    
    public PlayerScore()
    {
        name = "";
        score = -1;
        id = -1;
    }
    public PlayerScore(String n, int s)
    {
        name = n;
        score = s;
        id = -1;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    public void setScore(int s)
    {
        score = s;
    }
    public void setId(int i)
    {
        id = i;
    }
    public String getName()
    {
        return name;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getId()
    {
        return id;
    }
    
    @Override
    public String toString()
    {
        return " " + this.name + " " + this.score + "";
    }
}