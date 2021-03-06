/*************************************************************************
 * Author: Chris Rees
 * Date: 3/4/2010
 * File: PointMine.java
 * Purpose: A tweaked version of the Point class that allows me to store
 * the amount of mines within range of a given point. Does not extend
 * Point class because I don't want to have to cast ints for my X and Y
*************************************************************************/

package com.serneum.jsweeper.engine;

public class PointMine
{
	private int x;
	private int y;
	private int mines;
	private boolean checked;
	
	public PointMine()
	{
		this.x = 0;
		this.y = 0;
		this.mines = 0;
		this.checked = false;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getMines()
	{
		return this.mines;
	}
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setMines(int mines)
	{
		this.mines = mines;
	}
	
	public void setCheck(boolean checked)
	{
		this.checked = checked;
	}
	
	public boolean isChecked()
	{
		return this.checked;
	}
}
