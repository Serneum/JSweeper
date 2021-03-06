/*************************************************************************
 * Author: Chris Rees
 * Date: 3/3/2010
 * File: Mines.java
 * Purpose: Handles all of the information on mines within range of a 
 * given block.
*************************************************************************/

package com.serneum.jsweeper.engine;

import java.util.Random;

import javax.swing.JButton;

public class Mines
{
	private static PointMine[][] mines;
	private static int count;
	public static int uncovered = 0;
	
	private static void initMines(int height, int width)
	{
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				mines[i][j] = new PointMine();
	}
	
	public static int[][] initArrays(int[][] List, int height, int width, int mines)
	{
		Random gen = new Random();
		int count = 0, temp = 0;
		//Initialize the entire 2D array to 0
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				List[i][j] = 0;
		
		//Place mines within the array
		while(count < mines)
		{
			for(int i = 0; i < height; i++)
			{
				for(int j = 0; j < width; j++)
				{
					temp = gen.nextInt(100) + 1;
					
					if(List[i][j] != 1)
					{
						if(temp > 95 && count < mines)
						{
							List[i][j] = 1;
							count++;
						}
						else
							List[i][j] = 0;
					}
				}
			}
		}
		
		return List;
	}
	
	//Run once at every new game to predetermine position mine counts for quicker access
	public static void mineSet(int height, int width, int[][] List)
	{
		mines = new PointMine[height][width];
		
		initMines(height, width);
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				count = 0;
				mines[i][j].setLocation(j,i);
				if(List[i][j] == 1)
					mines[i][j].setMines(99);
				else
				{
					if(i > 0)
					{
						if(j > 0 && List[i-1][j-1] == 1)
							count++;
						if(List[i-1][j] == 1)
							count++;
						if(j < width - 1 && List[i-1][j+1] == 1)
							count++;
					}
					
					if(j > 0 && List[i][j-1] == 1)
						count++;
					if(j < width - 1 && List[i][j+1] == 1)
						count++;
					
					if(i < height - 1)
					{
						if(j > 0 && List[i+1][j-1] == 1)
							count++;
						if(List[i+1][j] == 1)
							count++;
						if(j < width - 1 && List[i+1][j+1] == 1)
							count++;
					}
					mines[i][j].setMines(count);
				}
			}
		}
	}
	
	public static int cascade(int x, int y, int height, int width, JButton[][] grid)
	{
		if(x < 0 || x >= width || y < 0 || y >= height)
			return 0;
			
		int mine = mines[y][x].getMines();
		
		if(mine == 99) 
            return 1; 
        if(mines[y][x].isChecked()) 
            return 0; 
        mines[y][x].setCheck(true);
        uncovered++;
                  
        //Full check 
        if(mine == 0) 
        { 
             cascade(x - 1, y, height, width, grid); 
             cascade(x - 1, y - 1, height, width, grid);
             cascade(x, y - 1, height, width, grid);
             cascade(x + 1, y - 1, height, width, grid);
             cascade(x + 1, y, height, width, grid);
             cascade (x + 1, y + 1, height, width, grid);
             cascade (x, y + 1, height, width, grid);
             cascade (x - 1, y + 1, height, width, grid);
             grid[y][x].setText(""); 
        } 
         
         else 
        	 grid[y][x].setText("" + mine); 
        
        grid[y][x].setEnabled(false);
         
        return 0; 
	}
}
