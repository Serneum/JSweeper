/*************************************************************************
 * Author: Chris Rees
 * Date: 3/3/2010
 * File: Game.java
 * Purpose: The main class that handles the GUI and user interactions
*************************************************************************/

package com.serneum.jsweeper.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.serneum.jsweeper.engine.*;

public class Game extends JFrame
{
	public static int level = 0;
	private int height = 0, width = 0, mines = 0, x, y, flags = 0, time = 0;
	private int[][] List;
	private JButton[][] grid;
	private Point pos = new Point();
	public static JLabel timer, mine;
	
	public static ImageIcon BlackMine, Flag;
	ImageIcon Ques = new ImageIcon("?");
	
	private mHandler mHandler;
	public static handler bHandler;
	private tHandler tHandler;
	
	Timer clock;
	
	public Game()
	{			
		initHandlers();
		Images.initImages();
		JMenuBar menu = MenuBar.createMenu();
		initStats();
		
		timer = new JLabel("Time: " + time);
		mine = new JLabel("Mines: " + mines);
		List = new int[height][width];
		List = Mines.initArrays(List, height, width, mines);
		Mines.mineSet(height, width, List);
		
		//Define pane and add elements
		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = width/2;
		c.anchor = GridBagConstraints.LINE_START;
		pane.add(timer, c);
		
		if(level == 1)
			c.gridx = width/2 + 1;
		else
			c.gridx = width/2;
		
		c.anchor = GridBagConstraints.LINE_END;
		pane.add(mine, c);
		
		//Create and initialize all buttons
		//Add button listener to all buttons
		grid = new JButton[height][width];
		
		Container frame = new Container();
		frame.setLayout(new GridLayout(height, width));
		
		Insets in = new Insets(0,0,0,0);
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				grid[i][j] = new JButton("");
				grid[i][j].addMouseListener(mHandler);
				grid[i][j].setPreferredSize(new Dimension(20,20));
				grid[i][j].setMargin(in);
				grid[i][j].setFocusable(false);
				frame.add(grid[i][j]);
			}
		}
		
		c.gridwidth = width;
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		pane.add(frame, c);
		
		clock = new Timer(1000, tHandler);
		clock.start();
		
		//Set defaults
		setJMenuBar(menu);
  		setTitle("JSweeper");
		setResizable(false);
  		pack();
  		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initHandlers()
	{
		//Create a button handler to determine which button is pressed
		bHandler = new handler();
		mHandler = new mHandler();
		tHandler = new tHandler();
	}	
	
	private void initStats()
	{
		if(level == 1)
		{
			height = 9;
			width = 9;
			mines = 10;
		}
		else if(level == 2)
		{
			height = 16;
			width = 16;
			mines = 40;
		}
		else if(level == 3)
		{
			height = 16;
			width = 30;
			mines = 99;
		}
	}
	
	private class mHandler implements MouseListener
	{
		public void mouseClicked(MouseEvent e)
		{
			pos = e.getComponent().getParent().getMousePosition();
			x = (int)pos.getX()/20;
			y = (int)pos.getY()/20;
			
			if(e.getButton() == MouseEvent.BUTTON1 && grid[y][x].getIcon() == null)
			{				
				if(Mines.cascade(x, y, height, width, grid) == 1)
	  			{
	  				//Placeholder Frame
	  				Frame frame = new Frame();

	  				//Stop buttons from working
	  				grid[y][x].setIcon(BlackMine);
	  				grid[y][x].setEnabled(false);
	  				
	  				for(int i = 0; i < height; i++)
	  					for(int j = 0; j < width; j++)
	  					{
	  						if(List[i][j] == 1)
	  						{
	  							grid[i][j].setIcon(BlackMine);
	  							grid[i][j].setEnabled(false);
	  						}
	  						grid[i][j].setRolloverEnabled(false);
	  						grid[i][j].removeMouseListener(this);
	  					}
	  				
	  				JOptionPane.showMessageDialog(frame, "Game Over.");
	  			}
	  			
	  			if(Mines.uncovered == (height * width) - mines)
	  			{
	  				Frame frame = new Frame();
	  				
	  				for(int i = 0; i < height; i++)
	  					for(int j = 0; j < width; j++)
	  						grid[i][j].removeMouseListener(this);
	  				
	  				JOptionPane.showMessageDialog(frame, "You Win!");
	  			}
			}
			else if(e.getButton() == MouseEvent.BUTTON3)
			{
				if(grid[y][x].getIcon() == null && grid[y][x].isEnabled())
				{
					grid[y][x].setIcon(Flag);
					grid[y][x].setRolloverEnabled(false);
					flags++;
					mine.setText("Mines: " + (mines - flags));
				}
				else if(grid[y][x].getIcon() == Flag)
				{
					grid[y][x].setIcon(Ques);
					grid[y][x].setText("?");
					flags--;
					mine.setText("Mines: " + (mines - flags));
				}
				else if(grid[y][x].getIcon() == Ques)
				{
					grid[y][x].setIcon(null);
					grid[y][x].setText("");
					grid[y][x].setRolloverEnabled(true);
				}
			}
		}
		public void mouseReleased(MouseEvent e)
		{
		}
		public void mousePressed(MouseEvent e)
		{
		}
		public void mouseEntered(MouseEvent e)
		{
		}
		public void mouseExited(MouseEvent e)
		{
		}
	}
	
	private class handler implements ActionListener
	{
	  	public void actionPerformed(ActionEvent e)
	  	{
	  		if("Quit".equals(e.getActionCommand()))
	  			System.exit(0);
	  		else if("Diff".equals(e.getActionCommand()))
	  		{
	  			new DifficultyMenu();
	  			clock.stop();
	  			dispose();
	  		}
	  		else if("New".equals(e.getActionCommand()))
	  		{
	  			reset();
	  			time = 0;
	  			timer.setText("Time: " + time);
	  		}
	  		else if("About".equals(e.getActionCommand()))
	  		{  				
  				new About();
	  		}
	  	}
	}
	
	private class tHandler implements ActionListener
	{
	  	public void actionPerformed(ActionEvent e)
	  	{
	  		time++;
	  		timer.setText("Time: " + time);
	  	}
	}
	
	public void reset()
	{
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
			{
				grid[i][j].setEnabled(true);
				grid[i][j].setIcon(null);
				grid[i][j].setText("");
				grid[i][j].addMouseListener(mHandler);
			}
		
		List = Mines.initArrays(List, height, width, mines);
		Mines.mineSet(height, width, List);
	}
}
